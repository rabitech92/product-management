package com.products.controller;

import com.products.model.Product;
import com.products.model.ActiveStatus;
import com.products.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/list")
    public List<Product> productList() {
        return productService.getAllProducts();
    }

    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return productService.getAllProducts(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @GetMapping("/get")
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        createdProduct.setActiveStatus(ActiveStatus.ACTIVE);
        createdProduct.setCreatedAt(product.getCreatedAt());
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/get/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return productService.updateProduct(id,updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateStock(@PathVariable Long id, @RequestParam int stockQuantity) {
        productService.updateStock(id, stockQuantity);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElse(null);
    }



}
