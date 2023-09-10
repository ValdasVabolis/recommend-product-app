package com.seb.valdas.productrecommendationservice.products.repository;

import com.seb.valdas.productrecommendationservice.products.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    Product save(Product product);
}
