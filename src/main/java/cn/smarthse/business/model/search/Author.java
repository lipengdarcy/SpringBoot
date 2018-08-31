package cn.smarthse.business.model.search;

import java.io.Serializable;

import lombok.Data;

/**
 * elasticsearch 测试model类：作者
 */
public @Data class Author implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 作者id
	 */
	private Long id;
	/**
	 * 作者姓名
	 */
	private String name;
	/**
	 * 作者简介
	 */
	private String remark;

}
