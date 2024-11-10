package com.products.repository;

import com.products.model.Product;
import com.products.enums.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
    Optional<Product>  findProductByIdAndActiveStatus(Long id, ActiveStatus activeStatus);
    Product findAllByActiveStatus(ActiveStatus activeStatus);

}
