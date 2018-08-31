package cn.smarthse.business.dao.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.smarthse.business.model.search.Article;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {
	
}