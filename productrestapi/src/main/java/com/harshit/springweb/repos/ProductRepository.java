package com.harshit.springweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.springweb.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
