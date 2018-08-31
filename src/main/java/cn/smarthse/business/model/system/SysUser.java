package cn.smarthse.business.model.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class SysUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户角色id列表
	 * 
	 * 角色说明 0：管理员 1：检测主管 2：检测人员 3：原始记录评审人员 4：仪器管理人员 5：报告管理人员 6：报告编制人员
	 */

	private Integer[] roleList;


	/**
	 * 用户角色名称（多个）
	 */
	private String roleName;

	/**
	 */
	private Integer id;

	/**
	 * 企业编号
	 */
	private Integer cid;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String passWord;

	/**
	 * 盐值
	 */
	private String salt;

	/**
	 * 姓名
	 */
	private String realName;

	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 联系电话
	 */
	private String tel;

	/**
	 * 工号
	 */
	private String staffNo;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 用户头像
	 */
	private Integer userIcon;

	/**
	 * 是否有效
	 */
	private Byte isValid;

}