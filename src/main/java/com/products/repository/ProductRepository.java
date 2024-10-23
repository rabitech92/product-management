package com.products.repository;

import com.products.model.Product;
import com.products.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
    Optional<Product>  findProductByIdAndStatus(Long id, Status status);

}
