package com.cohort5projectrest.Services;

import com.cohort5projectrest.Entities.Product;
import com.cohort5projectrest.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @PostConstruct
//    public void createProducts(){
//        List<Product> products = IntStream.rangeClosed(1, 50)
//                .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(5000)))
//                .collect(Collectors.toList());
//
//        productRepository.saveAll(products);
//    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsWithSorting(String field){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Product> getProductsWithPagination(int offset, int pageSize){
        return productRepository.findAll(PageRequest.of(offset, pageSize));
    }


    public Page<Product> getProductsWithPaginationAndSort(int offset, int pageSize, String field){
        return productRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
    }
}
