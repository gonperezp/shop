package com.ecommerce.shop.domain.port;

import com.ecommerce.shop.domain.model.Price;

public interface GetPriceUseCase {

	Price getPrice(final String applicationDate, final Integer productId, final Integer brandId);
}
