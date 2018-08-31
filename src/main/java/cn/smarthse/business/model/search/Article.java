package cn.smarthse.business.model.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * elasticsearch 测试model类：文章
 * 
 * @Document注解会对实体中的所有属性建立索引
 */

@Document(indexName = "projectname", type = "article", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
public @Data class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	/** 标题 */
	private String title;

	/** 摘要 */
	private String abstracts;

	/** 内容 */
	private String content;

	/** 发表时间 */
	private Date postTime;

	/** 点击率 */
	private Long clickCount;

	/** 作者 */
	private Author author;

	/** 所属教程 */
	private Tutorial tutorial;

	@Override
	public String toString() {
		return "Article{" + "id=" + id + ", title='" + title + '\'' + ", abstracts='" + abstracts + '\'' + ", content='"
				+ content + '\'' + ", postTime=" + postTime + ", clickCount=" + clickCount + ", author=" + author
				+ ", tutorial=" + tutorial + '}';
	}
}
