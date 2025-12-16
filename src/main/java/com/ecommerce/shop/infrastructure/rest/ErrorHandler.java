package com.ecommerce.shop.infrastructure.rest;


import com.ecommerce.shop.domain.exception.PriceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.openapitools.model.ErrorResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

import static com.ecommerce.shop.domain.constant.ErrorMessages.*;
import static com.ecommerce.shop.domain.constant.ExternalErrorCode.*;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handlePriceNotFound(PriceNotFoundException ex) {
		ErrorResponseDTO error = new ErrorResponseDTO();
		error.setMessage(ex.getMessage());
		error.setTitle(NOT_FOUND);
		error.setErrorCode(NOT_FOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponseDTO> handleMissingParameter(MissingServletRequestParameterException ex) {
		return buildBadRequestError(REQUIRED, REQUIRED_CODE, ex.getMessage());
	}

	@ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(Exception ex) {
		return buildBadRequestError(INVALID_REGEX, INVALID_REGEX_CODE, ex.getMessage());
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<ErrorResponseDTO> handleDateTimeParse(DateTimeParseException ex) {
		return buildBadRequestError(INVALID_DATETIME, INVALID_DATETIME_CODE, ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponseDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		return buildBadRequestError(INVALID_TYPE, INVALID_TYPE_CODE, ex.getMessage());
	}

	private ResponseEntity<ErrorResponseDTO> buildBadRequestError(String title, String errorCode, String message) {
		ErrorResponseDTO dto = new ErrorResponseDTO();
		dto.setTitle(title);
		dto.setErrorCode(errorCode);
		dto.setMessage(message);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleInternalError (Exception ex){
		ErrorResponseDTO error = new ErrorResponseDTO();
		error.setMessage(ex.getMessage());
		error.setTitle(INTERNAL_ERROR);
		error.setErrorCode(INTERNAL_ERROR_CODE);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}