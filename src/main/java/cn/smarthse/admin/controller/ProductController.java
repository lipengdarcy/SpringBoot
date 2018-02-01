package cn.smarthse.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.ServiceException;

import cn.smarthse.admin.service.ProductService;
import cn.smarthse.core.model.Product;
import cn.smarthse.core.model.common.ResponseData;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{id}")
	public ResponseData<Product> getProduct(@PathVariable("id") Long productId) throws ServiceException {
		Product p = productService.select(productId);
		return new ResponseData<Product>(p);
	}

	@GetMapping
	public ResponseData<List<Product>> getAllProduct() {
		return new ResponseData<List<Product>>(productService.getAllProduct());
	}

	@PutMapping("/{id}")
	public ResponseData<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody Product newProduct)
			throws ServiceException {
		return new ResponseData<Product>(productService.update(productId, newProduct));
	}

	@DeleteMapping("/{id}")
	public ResponseData<Boolean> deleteProduct(@PathVariable("id") long productId) throws ServiceException {
		return new ResponseData<Boolean>(productService.delete(productId));
	}

	@PostMapping
	public ResponseData<Boolean> addProduct(@RequestBody Product newProduct) throws ServiceException {
		return new ResponseData<Boolean>(productService.add(newProduct));
	}
}
