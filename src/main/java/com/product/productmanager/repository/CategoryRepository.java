package com.product.productmanager.repository;

import com.product.productmanager.entities.Category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {



    Optional<Category> findByName(String name);

    

}
