package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
