package com.example.rediscache.service;

import com.example.rediscache.dao.ProductRepository;
import com.example.rediscache.entity.Product;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "product",key = "#root.methodName")
    public List<Product> getAllProducts() {
        List<Product> products=productRepository.findAll();
        return products;
    }

    @Cacheable(value = "product",key = "#id")
    public Product findProductById(int id) {
        Product product=productRepository.findById(id).get();
        return product;
    }

    @CachePut(value = "product",key = "#product.id")
    public Product addProduct(Product product){
        Product product1=productRepository.save(product);
        return product1;
    }

    @CacheEvict(value = "product",key = "#id")
    public String deleteProduct(int id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
