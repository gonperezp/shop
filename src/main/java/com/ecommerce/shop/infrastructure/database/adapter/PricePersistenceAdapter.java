package com.ecommerce.shop.infrastructure.database.adapter;

import com.ecommerce.shop.domain.port.PricePersistencePort;
import com.ecommerce.shop.infrastructure.database.mapper.PricePersistenceMapper;
import com.ecommerce.shop.infrastructure.database.repository.PriceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PricePersistencePort {

	@NonNull
	private final PriceRepository priceRepository;

	@NonNull
	private final PricePersistenceMapper mapper;

	@Override
	public List<com.ecommerce.shop.domain.model.Price> getPriceByParameters(final Integer productId, final Integer brandId, final LocalDateTime applicationDate) {
		return this.mapper.priceDTOListToPriceList(this.priceRepository.findByProductIdAndBrandId(productId, brandId, applicationDate));
	}
}
