package cn.smarthse.business.controller.search;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.smarthse.business.dao.search.ArticleSearchRepository;
import cn.smarthse.business.model.search.Article;
import cn.smarthse.business.model.search.Author;
import cn.smarthse.business.model.search.Tutorial;
import cn.smarthse.framework.model.ResponseData;

/**
 * elasticsearch 测试
 */

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private ArticleSearchRepository articleSearchRepository;

	/**
	 * 建立索引
	 * 
	 * Document注解里面的几个属性，类比mysql的话是这样：
	 * 
	 * index –> DB
	 *
	 * type –> Table
	 * 
	 * Document –> row
	 * 
	 * @Id注解加上后，在Elasticsearch里相应于该列就是主键了，在查询时就可以直接用主键查询。
	 * 
	 */
	@RequestMapping("/add")
	public ResponseData<Article> testSaveArticleIndex() {
		ResponseData<Article> data = new ResponseData<Article>();

		Author author = new Author();
		author.setId(1L);
		author.setName("tianshouzhi");
		author.setRemark("java developer");

		Tutorial tutorial = new Tutorial();
		tutorial.setId(1L);
		tutorial.setName("elastic search");

		Article article = new Article();
		article.setId(1L);
		article.setTitle("springboot integreate elasticsearch");
		article.setAbstracts("springboot integreate elasticsearch is very easy");
		article.setTutorial(tutorial);
		article.setAuthor(author);
		article.setContent("elasticsearch based on lucene," + "spring-data-elastichsearch based on elaticsearch"
				+ ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
		article.setPostTime(new Date());
		article.setClickCount(1L);

		articleSearchRepository.save(article);
		data.setData(article);
		data.setMessage("新增数据成功！");
		return data;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/query")
	public ResponseData<List<Article>> testSearch() {
		ResponseData<List<Article>> data = new ResponseData<List<Article>>();
		String queryString = "springboot";// 搜索关键字
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
		Iterable<Article> searchResult = articleSearchRepository.search(builder);
		Iterator<Article> iterator = searchResult.iterator();
		data.setData(IteratorUtils.toList(iterator));		
		data.setMessage("查询成功！");
		return data;
	}
}