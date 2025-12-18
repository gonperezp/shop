package com.ecommerce.shop.domain.service;

import com.ecommerce.shop.domain.exception.PriceNotFoundException;
import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.port.PricePersistencePort;
import com.ecommerce.shop.domain.utils.DateUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecommerce.shop.domain.constant.ErrorMessages.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PriceService {

	@NonNull
	private final PricePersistencePort pricePersistenceAdapter;

	public Price getPrice(final String applicationDate, final Integer productId, final Integer brandId) {

		final List<Price> priceList = this.pricePersistenceAdapter.getPriceByParameters(productId, brandId, DateUtils.stringToLocalDateTime(applicationDate));

		if (priceList.isEmpty()) {
			throw new PriceNotFoundException(NOT_FOUND);
		}
		return priceList.getFirst();
	}

}
