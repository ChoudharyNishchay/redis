package com.example.rediscache.controller;


import com.example.rediscache.entity.Product;
import com.example.rediscache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
      List<Product> productList=productService.getAllProducts();
      return ResponseEntity.status(HttpStatus.FOUND).body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
      Product product=productService.findProductById(id);
      return ResponseEntity.status(HttpStatus.FOUND).body(product);
    }

    @PostMapping
    public  ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product product1=productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        String msg=productService.deleteProduct(id);
        return ResponseEntity.ok(msg);
    }


}
