package br.com.winter.core.repository;

import br.com.winter.core.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    Boolean existsByCode(String code);
}
