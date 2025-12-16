package com.ecommerce.shop.application.rest;

import com.ecommerce.shop.application.rest.mapper.PriceMapper;
import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.port.PriceServicePort;
import com.ecommerce.shop.domain.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceResponseDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

	private static final Integer PRODUCT_ID = 35455;

	private static final Integer BRAND_ID = 1;

	private static final String APPLICATION_DATE = DateUtils.localDateTimeToString(LocalDateTime.now());

	private static final  String START_DATE = DateUtils.localDateTimeToString(LocalDateTime.now().minusDays(2));

	private static final  String END_DATE = DateUtils.localDateTimeToString(LocalDateTime.now().plusDays(2));

	@Mock
	private PriceServicePort priceServicePort;

	@Mock
	private PriceMapper mapper;

	@InjectMocks
	private PriceController priceController;

	@Test
	void givenValidParametersWhenGetPriceThenReturnsOK() {

		final Price price = this.getPrice();
		final PriceResponseDTO priceResponseDTO = getPriceResponseDTO();

		when(this.priceServicePort.getPrice(APPLICATION_DATE, PRODUCT_ID, BRAND_ID)).thenReturn(price);
		when(this.mapper.priceToPriceResponseDTO(price)).thenReturn(priceResponseDTO);

		final ResponseEntity<PriceResponseDTO> response = this.priceController.getPrice(APPLICATION_DATE, PRODUCT_ID, BRAND_ID);

		assertNotNull(response);
		assertEquals(OK, response.getStatusCode());
		assertEquals(priceResponseDTO, response.getBody());

		verify(this.priceServicePort).getPrice(APPLICATION_DATE, PRODUCT_ID, BRAND_ID);
		verify(this.mapper).priceToPriceResponseDTO(price);
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

	private PriceResponseDTO getPriceResponseDTO() {

		final PriceResponseDTO priceResponseDTO = new PriceResponseDTO();
		priceResponseDTO.setBrandId(1);
		priceResponseDTO.setProductId(35455);
		priceResponseDTO.setStartDate(START_DATE);
		priceResponseDTO.setEndDate(END_DATE);
		priceResponseDTO.setRate(1);
		priceResponseDTO.setPrice(25.45);
		priceResponseDTO.setCurrency("EUR");

		return priceResponseDTO;
	}

}