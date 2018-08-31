package cn.smarthse.business.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smarthse.business.model.system.SysUser;
import cn.smarthse.business.model.system.SysUserExample;
import cn.smarthse.framework.generic.GenericDao;

public interface SysUserMapper extends GenericDao<SysUser> {

	/**
	 * 根据用户角色查询用户
	 * @param name
	 * @param type
	 * @return
	 */
	List<SysUser> queryByRole(@Param("name") String name,@Param("type") Integer type);

	/**
	 * 自定义查询用户列表
	 * 
	 * @param name
	 *            用户名或者姓名
	 */
	List<SysUser> query(@Param("name") String name);

	/**
	 * 查询用户所有权限
	 * 
	 * @param uid
	 *            用户id
	 */
	List<String> getPermission(Integer uid);

	int countByExample(SysUserExample example);

	int deleteByExample(SysUserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	List<SysUser> selectByExample(SysUserExample example);

	SysUser selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

	int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);
}