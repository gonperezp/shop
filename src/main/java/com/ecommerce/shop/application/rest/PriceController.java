package com.ecommerce.shop.application.rest;

import com.ecommerce.shop.application.rest.mapper.PriceMapper;
import com.ecommerce.shop.domain.port.PriceServicePort;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PriceApi;

import org.openapitools.model.PriceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Valid
@RequiredArgsConstructor
public class PriceController implements PriceApi {

	@NonNull
	private final PriceServicePort priceServicePort;

	@NonNull
	private final PriceMapper mapper;

	@Override
	public ResponseEntity<PriceResponseDTO> getPrice(final String applicationDate, final Integer productId, final  Integer brandId) {
		PriceResponseDTO response = this.mapper.priceToPriceResponseDTO(this.priceServicePort.getPrice(applicationDate, productId, brandId));
		return ResponseEntity.ok(response);
	}
}
