package com.products.service;

import com.products.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

     Product createProduct(Product product);
     Product updateProduct(Long id, Product product);
     void updateStock(Long id, int quantity);
     void deleteProduct(Long id);
     Optional<Product> getProductById(Long id);
     List<Product> getAllProducts();
     Page<Product> getAllProducts(Pageable pageable);
}
