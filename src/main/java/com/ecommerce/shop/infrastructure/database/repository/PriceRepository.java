package com.ecommerce.shop.infrastructure.database.repository;

import com.ecommerce.shop.infrastructure.database.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

	List<Price> findByProductIdAndBrandId(Integer productId, Integer brandId);

}
