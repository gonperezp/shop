package com.ecommerce.shop.infrastructure.database.adapter;

import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.infrastructure.database.mapper.PricePersistenceMapper;
import com.ecommerce.shop.infrastructure.database.repository.PriceRepository;

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
class PricePersistenceAdapterTest {

	private static final Integer PRODUCT_ID = 35455;

	private static final Integer BRAND_ID = 1;

	private static final  LocalDateTime START_DATE = LocalDateTime.now().minusDays(2);

	private static final  LocalDateTime END_DATE = LocalDateTime.now().plusDays(2);

	@Mock
	private PriceRepository priceRepository;

	@Mock
	private PricePersistenceMapper mapper;

	@InjectMocks
	private PricePersistenceAdapter pricePersistenceAdapter;

	@Test
	void givenValidParametersWhenGetPriceByParametersThenOk() {

		List<com.ecommerce.shop.infrastructure.database.entity.Price> entityPriceList = getEntityPrices();
		List<Price> domainPriceList = getDomainPrices();

		when(this.priceRepository.findByProductIdAndBrandId(PRODUCT_ID, BRAND_ID)).thenReturn(entityPriceList);
		when(this.mapper.priceDTOListToPriceList(entityPriceList)).thenReturn(domainPriceList);

		final List<Price> result = this.pricePersistenceAdapter.getPriceByParameters(PRODUCT_ID, BRAND_ID);

		assertNotNull(result);

		verify(this.priceRepository).findByProductIdAndBrandId(PRODUCT_ID, BRAND_ID);
		verify(this.mapper).priceDTOListToPriceList(entityPriceList);

	}

	private List<Price> getDomainPrices() {
		return List.of(
				Price.builder().id(1L).productId(PRODUCT_ID).brandId(BRAND_ID).priority(1).priceList(2).startDate(START_DATE).endDate(END_DATE).build(),
				Price.builder().id(2L).productId(PRODUCT_ID).brandId(BRAND_ID).priority(0).priceList(5).startDate(START_DATE).endDate(END_DATE).build()
		);
	}

	List<com.ecommerce.shop.infrastructure.database.entity.Price> getEntityPrices() {
		return List.of(
				com.ecommerce.shop.infrastructure.database.entity.Price.builder()
						.id(1L).productId(PRODUCT_ID).brandId(BRAND_ID).priority(1).priceList(2).startDate(START_DATE).endDate(END_DATE).build(),
				com.ecommerce.shop.infrastructure.database.entity.Price.builder()
						.id(2L).productId(PRODUCT_ID).brandId(BRAND_ID).priority(0).priceList(5).startDate(START_DATE).endDate(END_DATE).build()
		);
	}

}