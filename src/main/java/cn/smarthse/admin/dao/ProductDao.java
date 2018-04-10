package cn.smarthse.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smarthse.admin.model.Product;


public interface ProductDao {
	
	Product select(@Param("id") long id);

	Integer update(Product product);

	Integer insert(Product product);

	Integer delete(long productId);

	List<Product> getAllProduct();
}