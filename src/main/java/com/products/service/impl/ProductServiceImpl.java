package com.products.service.impl;

import com.products.model.Product;
import com.products.model.ActiveStatus;
import com.products.repository.ProductRepository;
import com.products.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().filter(product -> product.getActiveStatus().equals(ActiveStatus.ACTIVE)).collect(Collectors.toList());
    }

    @Override
    public Product createProduct(Product product) {
        if (productRepository.findByName(product.getName()).isPresent()){
            throw new IllegalArgumentException("Product name must be unique");
        }
        LocalDateTime currentDate = LocalDateTime.now();
        product.setCreatedAt(currentDate);
        product.setUpdatedAt(currentDate);
        product.setActiveStatus(ActiveStatus.ACTIVE);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).get();
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product not found");

        }else {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setUpdatedAt(LocalDateTime.now());
            existingProduct.setCategory(product.getCategory());
            return productRepository.save(existingProduct);
        }
    }

    @Override
    public void updateStock(Long id, int quantity) {
        Product product = productRepository.findById(id).get();
        if (product.getActiveStatus() == ActiveStatus.ACTIVE){
            product.setStockQuantity(quantity);
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> exitProduct = productRepository.findProductByIdAndActiveStatus(id, ActiveStatus.ACTIVE);
        if(exitProduct.isPresent()){
            Product product = exitProduct.get();
            product.setActiveStatus(ActiveStatus.DELETE);
            productRepository.save(product);

        }

    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // New method for paginated and sorted product list
    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


}
