package com.ecommerce.shop.infrastructure.rest.controller;

import com.ecommerce.shop.infrastructure.rest.mapper.PriceMapper;
import com.ecommerce.shop.domain.port.GetPriceUseCase;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.PriceApi;

import org.openapitools.model.PriceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PriceController implements PriceApi {

	@NonNull
	private final GetPriceUseCase getPriceUseCase;

	@NonNull
	private final PriceMapper mapper;

	@Override
	public ResponseEntity<PriceResponseDTO> getPrice(final String applicationDate, final Integer productId, final  Integer brandId) {
		PriceResponseDTO response = this.mapper.priceToPriceResponseDTO(this.getPriceUseCase.getPrice(applicationDate, productId, brandId));
		return ResponseEntity.ok(response);
	}
}
