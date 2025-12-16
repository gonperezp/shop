package com.ecommerce.shop.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Price {

	private Long id;

	private Integer brandId;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Integer priceList;

	private Integer productId;

	private Integer priority;

	private Double price;

	private String curr;
}
