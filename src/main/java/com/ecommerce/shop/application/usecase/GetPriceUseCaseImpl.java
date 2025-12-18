package com.ecommerce.shop.application.usecase;

import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.port.GetPriceUseCase;
import com.ecommerce.shop.domain.service.PriceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

	@NonNull
	private final PriceService priceService;

	@Override
	public Price getPrice(final String applicationDate, final Integer productId, final Integer brandId) {
		return this.priceService.getPrice(applicationDate, productId, brandId);
	}
}
