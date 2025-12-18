package com.ecommerce.shop.domain.service;

import com.ecommerce.shop.domain.exception.PriceNotFoundException;
import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.port.PricePersistencePort;
import com.ecommerce.shop.domain.utils.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

	private static final LocalDateTime DATE_NOW = LocalDateTime.now();

	private static final  String STRING_DATE  = DateUtils.localDateTimeToString(DATE_NOW);

	private static final  LocalDateTime LOCAL_DATE_TIME = DateUtils.stringToLocalDateTime(STRING_DATE);

	private static final Integer PRODUCT_ID = 35455;

	private static final Integer BRAND_ID = 1;

	@Mock
	private PricePersistencePort pricePersistenceAdapter;

	@InjectMocks
	private PriceService priceService;

	@Test
	@DisplayName("Given valid parameters and adapter return various prices when Get Price Then return highest priority price")
	void givenValidParametersAndGetVariousPricesFromAdapterWhenGetPriceThenOk() {

		when(this.pricePersistenceAdapter.getPriceByParameters(PRODUCT_ID, BRAND_ID, LOCAL_DATE_TIME)).thenReturn(this.getPrices());

		final Price price = this.priceService.getPrice(STRING_DATE, PRODUCT_ID, BRAND_ID);

		verify(this.pricePersistenceAdapter).getPriceByParameters(PRODUCT_ID, BRAND_ID, LOCAL_DATE_TIME);

		assertNotNull(price);
		assertEquals(1, price.getPriority());
		assertEquals(2, price.getPriceList());
	}

	@Test
	@DisplayName("Given valid parameters and adapter return only one price when Get Price Then Ok")
	void givenValidParametersAndGetOnePricesFromAdapterWhenGetPriceThenOk() {

		when(this.pricePersistenceAdapter.getPriceByParameters(PRODUCT_ID, BRAND_ID, LOCAL_DATE_TIME))
				.thenReturn(List.of(this.getPrices().get(1)));

		final Price price = this.priceService.getPrice(STRING_DATE, PRODUCT_ID, BRAND_ID);

		verify(this.pricePersistenceAdapter).getPriceByParameters(PRODUCT_ID, BRAND_ID, LOCAL_DATE_TIME);

		assertNotNull(price);
		assertEquals(0, price.getPriority());
		assertEquals(5, price.getPriceList());
	}

	@Test
	void givenValidParametersWhenGetPriceThenPriceNotFoundThenExceptionIsThrown() {

		when(this.pricePersistenceAdapter.getPriceByParameters(PRODUCT_ID, BRAND_ID, LOCAL_DATE_TIME)).thenReturn(List.of());

		assertThrows(PriceNotFoundException.class, () -> this.priceService.getPrice(STRING_DATE, PRODUCT_ID, BRAND_ID));

		verify(this.pricePersistenceAdapter).getPriceByParameters(PRODUCT_ID, BRAND_ID, LOCAL_DATE_TIME);
	}

	private List<Price> getPrices() {

		final LocalDateTime startDate = LocalDateTime.now().minusDays(2);
		final LocalDateTime endDate = LocalDateTime.now().plusDays(2);
		return List.of(
				Price.builder().id(1L).productId(PRODUCT_ID).brandId(BRAND_ID).priority(1).priceList(2).startDate(startDate).endDate(endDate).build(),
				Price.builder().id(2L).productId(PRODUCT_ID).brandId(BRAND_ID).priority(0).priceList(5).startDate(startDate).endDate(endDate).build()
		);
	}

}