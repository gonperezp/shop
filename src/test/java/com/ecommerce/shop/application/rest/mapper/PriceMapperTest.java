package com.ecommerce.shop.application.rest.mapper;

import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceResponseDTO;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

	PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

	@Test
	void givenPriceWhenMapThenPriceResponseDTO() {
		final Price input = this.getPrice();
		final PriceResponseDTO result = this.priceMapper.priceToPriceResponseDTO(input);

		assertNotNull(result);
		assertEquals(input.getBrandId(), result.getBrandId());
		assertEquals(input.getProductId(), result.getProductId());
		assertEquals(input.getPriceList(), result.getRate());
		assertEquals(input.getPrice(), result.getPrice());
		assertEquals(input.getCurr(), result.getCurrency());
		assertEquals(DateUtils.localDateTimeToString(input.getStartDate()), result.getStartDate());
		assertEquals(DateUtils.localDateTimeToString(input.getEndDate()), result.getEndDate());

	}

	private Price getPrice() {
		return Price.builder()
				.id(1L)
				.brandId(1)
				.startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now().plusDays(3))
				.priceList(1)
				.productId(35455)
				.priority(1)
				.price(25.45)
				.curr("EUR")
				.build();
	}

}