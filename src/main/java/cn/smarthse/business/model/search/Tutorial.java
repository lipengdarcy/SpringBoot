package cn.smarthse.business.model.search;

import java.io.Serializable;

import lombok.Data;

/**
 * 案例背景：每个文章(Article)都要属于一个教程(Tutorial),而且每个文章都要有一个作者(Author)。
 * 
 * elasticsearch 测试model类：教程
 */
public @Data class Tutorial implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;// 教程名称

}