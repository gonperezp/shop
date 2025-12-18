package com.ecommerce.shop.infrastructure.database.repository;

import com.ecommerce.shop.infrastructure.database.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {


	@Query("""
        SELECT p FROM Price p
        WHERE p.productId = :productId
          AND p.brandId = :brandId
          AND :applicationDate BETWEEN p.startDate AND p.endDate
        ORDER BY p.priority DESC
    """)
	List<Price> findByProductIdAndBrandId(final Integer productId, final Integer brandId, final LocalDateTime applicationDate);

}
