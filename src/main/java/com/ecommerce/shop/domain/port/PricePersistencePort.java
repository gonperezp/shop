package com.ecommerce.shop.domain.port;

import com.ecommerce.shop.domain.model.Price;

import java.util.List;

public interface PricePersistencePort {

	List<Price> getPriceByParameters(final Integer productId, final Integer brandId);

}
