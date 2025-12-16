package com.ecommerce.shop.domain.port;

import com.ecommerce.shop.domain.model.Price;

public interface PriceServicePort {

	Price getPrice(final String applicationDate, final Integer productId, final Integer brandId);
}
