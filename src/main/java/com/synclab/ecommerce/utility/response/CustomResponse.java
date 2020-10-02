package com.synclab.ecommerce.utility.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {

	public static <T> ResponseEntity<T> getFindResponse(T entity, String errorHeader, String errorMessage) {

		return entity != null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.NO_CONTENT).header(errorHeader, errorMessage).build();

	}

	public static <T> ResponseEntity<T> getDeleteResponse(T entity, String errorHeader, String errorMessage) {

		return entity == null ? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).header(errorHeader, errorMessage).build();

	}

	private CustomResponse() {
	}

}
