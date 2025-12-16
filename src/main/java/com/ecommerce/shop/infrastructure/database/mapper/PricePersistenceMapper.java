package com.ecommerce.shop.infrastructure.database.mapper;

import com.ecommerce.shop.infrastructure.database.entity.Price;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricePersistenceMapper {

	List<com.ecommerce.shop.domain.model.Price> priceDTOListToPriceList(final List<Price> priceList);

}
