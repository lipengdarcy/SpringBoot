package cn.smarthse.core.model.ASystem;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="User")
public class AUser implements Serializable {
	
	/**
	 * 是否管理员，判断依据是角色
	 */
	private Boolean isAdmin = false;

	/**
	 * 员工id
	 */
	private Integer staffId;


	private static final long serialVersionUID = -1253419956494415277L;

	/**
     */
	private Integer id;

	/**
	 * 企业id
	 */
	private Integer cid;

	/**
	 * 用户基础信息ID
	 */
	private Integer userbaseid;

	/**
	 * 头像文件ID
	 */
	private Integer faceid;

	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 登录验证码
	 */
	private String verification;

	/**
	 * 全名
	 */
	private String fullname;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 密码明文,md5加密
	 */
	private String plainpassword;

	/**
	 * 加密salt
	 */
	private String salt;

	/**
	 * 注册时间
	 */
	private Date registertime;

	/**
	 * 注册IP
	 */
	private String registerip;

	/**
	 * 注册方式: 1：PC网络注册 2：手机网络注册 3：管理员添加
	 */
	private Byte registertype;

	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 手机
	 */
	private String cellphone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 用户类型
	 */
	private Byte usertype;

	/**
	 * 是否有效
	 */
	private Byte isvalid;
	
	 /**
     *   创建时间
     */
    private Date createtime;

    /**
     *   创建人
     */
    private Integer creator;

    /**
     *   修改时间
     */
    private Date updatetime;

    /**
     *   修改人
     */
    private Integer updator;
    
    /**
     *   平台传过来的用户唯一标识
     */
    private String usertoken;

}