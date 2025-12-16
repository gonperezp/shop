package com.ecommerce.shop.infrastructure.database.mapper;

import com.ecommerce.shop.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PricePersistenceMapperTest {

	private static final Integer PRODUCT_ID = 35455;

	private static final Integer BRAND_ID = 1;

	private static final  LocalDateTime START_DATE = LocalDateTime.now().minusDays(2);

	private static final  LocalDateTime END_DATE = LocalDateTime.now().plusDays(2);

	PricePersistenceMapper pricePersistenceMapper = Mappers.getMapper(PricePersistenceMapper.class);

	@Test
	void givenPriceWhenMapThenPriceResponseDTO() {
		final List<com.ecommerce.shop.infrastructure.database.entity.Price> input = this.getEntityPrices();
		final List<Price> result = this.pricePersistenceMapper.priceDTOListToPriceList(input);

		assertNotNull(result);
		assertEquals(2, result.size());

		assertEquals(input.getFirst().getId(), result.getFirst().getId());
		assertEquals(input.getFirst().getProductId(), result.getFirst().getProductId());
		assertEquals(input.getFirst().getBrandId(), result.getFirst().getBrandId());
		assertEquals(input.getFirst().getPriority(), result.getFirst().getPriority());
		assertEquals(input.getFirst().getPriceList(), result.getFirst().getPriceList());
		assertEquals(input.getFirst().getStartDate(), result.getFirst().getStartDate());
		assertEquals(input.getFirst().getEndDate(), result.getFirst().getEndDate());
		assertEquals(input.getFirst().getPrice(), result.getFirst().getPrice());
		assertEquals(input.getFirst().getCurr(), result.getFirst().getCurr());

		assertEquals(input.getLast().getId(), result.getLast().getId());
		assertEquals(input.getLast().getProductId(), result.getLast().getProductId());
		assertEquals(input.getLast().getBrandId(), result.getLast().getBrandId());
		assertEquals(input.getLast().getPriority(), result.getLast().getPriority());
		assertEquals(input.getLast().getPriceList(), result.getLast().getPriceList());
		assertEquals(input.getLast().getStartDate(), result.getLast().getStartDate());
		assertEquals(input.getLast().getEndDate(), result.getLast().getEndDate());
		assertEquals(input.getLast().getPrice(), result.getLast().getPrice());
		assertEquals(input.getLast().getCurr(), result.getLast().getCurr());
	}

	List<com.ecommerce.shop.infrastructure.database.entity.Price> getEntityPrices() {
		return List.of(
				com.ecommerce.shop.infrastructure.database.entity.Price.builder()
						.id(1L)
						.productId(PRODUCT_ID)
						.brandId(BRAND_ID)
						.priority(1)
						.priceList(2)
						.startDate(START_DATE)
						.endDate(END_DATE)
						.price(24.56)
						.curr("EUR")
						.build(),
				com.ecommerce.shop.infrastructure.database.entity.Price.builder()
						.id(2L)
						.productId(PRODUCT_ID)
						.brandId(BRAND_ID)
						.priority(0)
						.priceList(5)
						.productId(PRODUCT_ID)
						.startDate(START_DATE)
						.endDate(END_DATE)
						.price(78.9)
						.curr("EUR")
						.build()
		);
	}
}
