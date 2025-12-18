package com.ecommerce.shop.application.usecase;

import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.service.PriceService;
import com.ecommerce.shop.domain.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseImplTest {

	private static final Integer PRODUCT_ID = 35455;

	private static final Integer BRAND_ID = 1;

	private static final String APPLICATION_DATE = DateUtils.localDateTimeToString(LocalDateTime.now());

	@Mock
	private PriceService priceService;

	@InjectMocks
	private GetPriceUseCaseImpl getPriceUseCaseImpl;

	@Test
	void givenValidParametersWhenGetPriceThenOk() {
		Mockito.when(this.priceService.getPrice(APPLICATION_DATE, PRODUCT_ID, BRAND_ID)).thenReturn(getPrices());
		final Price result = this.getPriceUseCaseImpl.getPrice(APPLICATION_DATE, PRODUCT_ID, BRAND_ID);
		assertNotNull(result);
		Mockito.verify(this.priceService).getPrice(APPLICATION_DATE, PRODUCT_ID, BRAND_ID);
	}

	private Price getPrices() {
		return Price.builder()
				.id(1L)
				.productId(PRODUCT_ID)
				.brandId(BRAND_ID)
				.priority(1)
				.priceList(2)
				.startDate(LocalDateTime.now().minusDays(2))
				.endDate(LocalDateTime.now().plusDays(2))
				.build();
	}

}