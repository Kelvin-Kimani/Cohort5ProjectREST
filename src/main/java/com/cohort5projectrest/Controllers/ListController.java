package com.cohort5projectrest.Controllers;

import com.cohort5projectrest.Entities.Product;
import com.cohort5projectrest.Services.ProductService;
import com.cohort5projectrest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ListController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping
    public Map<Map<String, Integer>, List<Product>> getAllProducts(){
        List<Product> products = productService.getProducts();
        return Map.of(Map.of("Records", products.size()) , products);
    }


    @GetMapping("/{field}")
    public Map<Map<String, Integer>, List<Product>> getAllProductsWithSorting(@PathVariable String field){
        List<Product> products = productService.getProductsWithSorting(field);
        return Map.of(Map.of("Records", products.size()) , products);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Map<Map<String, Integer>, Page<Product>> getAllProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        Page<Product> products = productService.getProductsWithPagination(offset, pageSize);
        return Map.of(Map.of("Records", products.getSize()) , products);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public Map<Map<String, Integer>, Slice<Product>> getAllProductsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<Product> products = productService.getProductsWithPaginationAndSort(offset, pageSize, field);

        //to avoid overhead, we use slice, which returns the current slice, and only knows if the next slice is available or not
        Slice<Product> sliced = productService.getProductsWithPaginationAndSort(offset, pageSize, field);
        return Map.of(Map.of("Records", sliced.getSize()) , sliced);
    }


    @GetMapping("/checkemail")
    public ResponseEntity<String> checkEmail(@RequestBody String email){
        boolean b = userService.validEmail(email);
        if (b){
            return ResponseEntity.ok("Email is valid");
        } else return ResponseEntity.badRequest().body("Email is invalid");
    }

    @GetMapping("/checkpassword")
    public ResponseEntity<String> checkPassword(@RequestBody String password){
        boolean b = userService.validPassword(password);
        if (b){
            return ResponseEntity.ok("Password is valid");
        } else return ResponseEntity.badRequest().body("Password is invalid");
    }

}
