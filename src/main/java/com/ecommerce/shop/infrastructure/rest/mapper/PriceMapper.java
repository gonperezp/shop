package com.ecommerce.shop.infrastructure.rest.mapper;

import com.ecommerce.shop.domain.model.Price;
import com.ecommerce.shop.domain.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.PriceResponseDTO;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PriceMapper {

	@Mapping(target = "rate", source = "priceList")
	@Mapping(target = "currency", source = "curr")
	@Mapping(target = "startDate", source = "startDate", qualifiedByName = "localDateTimeToString")
	@Mapping(target = "endDate", source = "endDate", qualifiedByName = "localDateTimeToString")
	PriceResponseDTO priceToPriceResponseDTO(final Price price);

	@Named("localDateTimeToString")
	default String localDateTimeToString(final LocalDateTime localDateTime) {
		return DateUtils.localDateTimeToString(localDateTime);
	}

}
