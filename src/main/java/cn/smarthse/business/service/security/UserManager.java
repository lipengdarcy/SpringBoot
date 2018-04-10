package cn.smarthse.business.service.security;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.smarthse.business.dao.AdminUserMapper;
import cn.smarthse.business.model.AdminUser;
import cn.smarthse.business.model.AdminUserExample;
import cn.smarthse.framework.Constant;
import cn.smarthse.framework.util.Digests;
import cn.smarthse.framework.util.Encodes;

/**
 * 用户管理类
 * 
 * @author lipeng
 */
@CacheConfig(cacheNames = Constant.redisCacheName)
@Service
public class UserManager {

	@Resource
	AdminUserMapper userMapper;

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	/**
	 * 根据用户id，获取用户实体
	 * 
	 * @param id
	 * @return SecUser
	 */
	@Cacheable(key = "'User-' + #id")
	public AdminUser getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据用户id，获取存在的用户实体
	 * 
	 * @param id
	 * @return SecUser
	 */
	public AdminUser selectUserById(Integer id) {
		AdminUserExample e = new AdminUserExample();
		e.createCriteria().andIsValidEqualTo(Constant.ACTIVE_YES).andIdEqualTo(id);
		List<AdminUser> list = userMapper.selectByExample(e);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@CacheEvict(key = "'User-byname-' + #name")
	public void clearUserByNameCache(String name) {
	}

	/**
	 * 根据用户名，获取用户实体
	 * 
	 * @param name
	 *            用户名
	 * @return User
	 */
	@Cacheable(key = "'AdminUser-byname-' + #name")
	public AdminUser getUserByName(String name) {
		AdminUserExample e = new AdminUserExample();
		e.createCriteria().andUserNameEqualTo(name).andIsValidEqualTo(Constant.ACTIVE_YES);
		List<AdminUser> list = userMapper.selectByExample(e);
		if (list.isEmpty())
			return null;
		AdminUser user = list.get(0);
		return user;
	}

	public int insert(AdminUser user) {
		// 判断是否重复
		AdminUser existUser = this.getUserByName(user.getUserName());
		if (existUser != null) {
			return -1;
		}
		// 密码处理
		byte[] saltbyte = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.hexEncode(saltbyte));
		String newMd5Password = (new SimpleHash("MD5", user.getPassWord())).toString();// 新的密码单次md5加密
		String newPassword = Encodes.hexEncode(Digests.sha1(newMd5Password.getBytes(), saltbyte, HASH_INTERATIONS));
		user.setPassWord(newPassword);
		user.setCreateTime(new Date());
		return userMapper.insertSelective(user);
	}

}
