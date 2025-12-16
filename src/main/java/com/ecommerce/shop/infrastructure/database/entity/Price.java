package com.ecommerce.shop.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="brand_id", nullable = false)
	private Integer brandId;

	@Column(name="start_date", nullable = false)
	private LocalDateTime startDate;

	@Column(name="end_date", nullable = false)
	private LocalDateTime endDate;

	@Column(name="price_list", nullable = false)
	private Integer priceList;

	@Column(name="product_id", nullable = false)
	private Integer productId;

	@Column(name="priority", nullable = false)
	private Integer priority;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name="curr", nullable = false, length = 3)
	private String curr;
}
