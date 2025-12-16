package com.ecommerce.shop.domain.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

	public static LocalDateTime stringToLocalDateTime (final String date) {
		return LocalDateTime.parse(date, FORMATTER);
	}

	public static String localDateTimeToString (final LocalDateTime date) {
		return date.format(FORMATTER);
	}
}
