package com.ecommerce.shop.infrastructure.rest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.ecommerce.shop.domain.constant.ErrorMessages.*;
import static com.ecommerce.shop.domain.constant.ExternalErrorCode.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

	private static final String URL = "/price";

	private static final String PRODUCT_ID_PARAM = "productId";

	private static final String PRODUCT_ID_PARAM_VALUE = "35455";

	private static final String BRAND_ID_PARAM = "brandId";

	private static final String BRAND_ID_PARAM_VALUE = "1";

	private static final String APPLICATION_DATE_PARAM = "applicationDate";

	@Autowired
	private MockMvc mockMvc;

	private ResultActions performEndpoint(final String applicationDate) throws Exception {
		return this.mockMvc.perform(
				get(URL)
				.param(APPLICATION_DATE_PARAM, applicationDate)
				.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
				.param(BRAND_ID_PARAM, BRAND_ID_PARAM_VALUE)
		);
	}

	@Test
	@DisplayName("200 OK when productId = 35455, brandId = 1 and applicationDate = 2020-06-14 10:00:00")
	void givenValidParametersAndDate1ThenReturns200() throws Exception {

		final String applicationDate = "2020-06-14 10:00:00";

		this.performEndpoint(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(35455))
				.andExpect(jsonPath("$.brandId").value(1))
				.andExpect(jsonPath("$.rate").value(1))
				.andExpect(jsonPath("$.startDate").value("2020-06-14 00:00:00"))
				.andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
				.andExpect(jsonPath("$.price").value(35.50))
				.andExpect(jsonPath("$.currency").value("EUR"));
	}

	@Test
	@DisplayName("200 OK when productId = 35455, brandId = 1 and applicationDate = 2020-06-14 16:00:00")
	void givenValidParametersAndDate2ThenReturns200() throws Exception {

		final String applicationDate = "2020-06-14 16:00:00";

		this.performEndpoint(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(35455))
				.andExpect(jsonPath("$.brandId").value(1))
				.andExpect(jsonPath("$.rate").value(2))
				.andExpect(jsonPath("$.startDate").value("2020-06-14 15:00:00"))
				.andExpect(jsonPath("$.endDate").value("2020-06-14 18:30:00"))
				.andExpect(jsonPath("$.price").value(25.45))
				.andExpect(jsonPath("$.currency").value("EUR"));
	}

	@Test
	@DisplayName("200 OK when productId = 35455, brandId = 1 and applicationDate = 2020-06-14 21:00:00")
	void givenValidParametersAndDate3ThenReturns200() throws Exception {

		final String applicationDate = "2020-06-14 21:00:00";

		this.performEndpoint(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(35455))
				.andExpect(jsonPath("$.brandId").value(1))
				.andExpect(jsonPath("$.rate").value(1))
				.andExpect(jsonPath("$.startDate").value("2020-06-14 00:00:00"))
				.andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
				.andExpect(jsonPath("$.price").value(35.50))
				.andExpect(jsonPath("$.currency").value("EUR"));
	}

	@Test
	@DisplayName("200 OK when productId = 35455, brandId = 1 and applicationDate = 2020-06-15 10:00:00")
	void givenValidParametersAndDate4ThenReturns200() throws Exception {

		final String applicationDate = "2020-06-15 10:00:00";

		this.performEndpoint(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(35455))
				.andExpect(jsonPath("$.brandId").value(1))
				.andExpect(jsonPath("$.rate").value(3))
				.andExpect(jsonPath("$.startDate").value("2020-06-15 00:00:00"))
				.andExpect(jsonPath("$.endDate").value("2020-06-15 11:00:00"))
				.andExpect(jsonPath("$.price").value(30.50))
				.andExpect(jsonPath("$.currency").value("EUR"));
	}

	@Test
	@DisplayName("200 OK when productId = 35455, brandId = 1 and applicationDate = 2020-06-16 21:00:00")
	void givenValidParametersAndDate5ThenReturns200() throws Exception {

		final String applicationDate = "2020-06-16 21:00:00";

		this.performEndpoint(applicationDate)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(35455))
				.andExpect(jsonPath("$.brandId").value(1))
				.andExpect(jsonPath("$.rate").value(4))
				.andExpect(jsonPath("$.startDate").value("2020-06-15 16:00:00"))
				.andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
				.andExpect(jsonPath("$.price").value(38.95))
				.andExpect(jsonPath("$.currency").value("EUR"));
	}

	@Test
	@DisplayName("404 NOT FOUND when productId = 35455, brandId = 2 and applicationDate = 2020-06-16 21:00:00")
	void givenValidParametersWhenNotFoundThenReturns404() throws Exception {

		final String applicationDate = "2020-06-16 21:00:00";

		this.mockMvc.perform(
						get(URL)
								.param(APPLICATION_DATE_PARAM, applicationDate)
								.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
								.param(BRAND_ID_PARAM, "2")
				)
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value(NOT_FOUND))
				.andExpect(jsonPath("$.errorCode").value(NOT_FOUND_CODE));
	}

	@Test
	@DisplayName("400 BAD REQUEST when productId = null, brandId = 1 and applicationDate = 2020-06-16 21:00:00")
	void givenInValidRequiredParameterThenReturns400() throws Exception {

		final String applicationDate = "2020-06-16 21:00:00";

		this.mockMvc.perform(
				get(URL)
						.param(APPLICATION_DATE_PARAM, applicationDate)
						.param(BRAND_ID_PARAM, BRAND_ID_PARAM_VALUE)
				)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.title").value(REQUIRED))
				.andExpect(jsonPath("$.errorCode").value(REQUIRED_CODE));
	}

	@Test
	@DisplayName("400 BAD REQUEST when productId = 35455, brandId = invalid and applicationDate = 2020-06-16 21:00:00")
	void givenInValidTypeParameterThenReturns400() throws Exception {

		final String applicationDate = "2020-06-16 21:00:00";

		this.mockMvc.perform(
						get(URL)
								.param(APPLICATION_DATE_PARAM, applicationDate)
								.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
								.param(BRAND_ID_PARAM, "invalid")
				)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.title").value(INVALID_TYPE))
				.andExpect(jsonPath("$.errorCode").value(INVALID_TYPE_CODE));
	}

}
