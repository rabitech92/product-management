package com.products.model;

import com.products.enums.ActiveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int stockQuantity;

    @Column(nullable = false)
    private String category;

    @DateTimeFormat(fallbackPatterns = "dd.mm.yyyy HH:mm:")
    private LocalDateTime createdAt;
    @DateTimeFormat(fallbackPatterns = "dd.mm.yyyy HH:mm:")
    private LocalDateTime updatedAt;
    @Enumerated
    private ActiveStatus activeStatus;

    public Product(String name, String description, BigDecimal price, Integer stockQuantity, String category) {
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        this.description = description;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Product(String name, String description, int stockQuantity) {
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.name = name;
    }

    // Business Rules
    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.price = price;
    }

    public void setStockQuantity(Integer stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity must be non-negative.");
        }
        this.stockQuantity = stockQuantity;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty.");
        }
        this.name = name;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
