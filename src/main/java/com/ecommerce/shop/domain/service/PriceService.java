package com.ecommerce.shop.domain.service;

import com.ecommerce.shop.domain.exception.PriceNotFoundException;
import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.port.PricePersistencePort;
import com.ecommerce.shop.domain.port.PriceServicePort;
import com.ecommerce.shop.domain.utils.DateUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceService implements PriceServicePort {

	@NonNull
	private final PricePersistencePort pricePersistenceAdapter;

	@Override
	public Price getPrice(final String applicationDate, final Integer productId, final Integer brandId) {

		final List<Price> priceList = this.pricePersistenceAdapter.getPriceByParameters(productId, brandId);

		final LocalDateTime localApplicationDate = DateUtils.stringToLocalDateTime(applicationDate);

		return priceList.stream()
				.filter(price -> this.isDateBetween(localApplicationDate, price.getStartDate(), price.getEndDate()))
				.max(Comparator.comparing(Price::getPriority))
				.orElseThrow(() -> new PriceNotFoundException("Price not found"));
	}

	private boolean isDateBetween(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
		return !date.isBefore(startDate) && !date.isAfter(endDate);
	}

}
