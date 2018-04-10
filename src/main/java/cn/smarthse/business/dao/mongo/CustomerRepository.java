package cn.smarthse.core.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.smarthse.core.model.mongo.Customer;

/**
 * mongodb 接口类
 */


public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
