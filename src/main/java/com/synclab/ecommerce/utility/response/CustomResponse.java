package com.synclab.ecommerce.utility.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {
		
	public static <T> ResponseEntity<T> getResponse(T entity, String emptyResponseHeader , String emptyResponseMessage){
		
		return entity != null 
				? ResponseEntity.ok(entity)
				: ResponseEntity.status(HttpStatus.NO_CONTENT).header(emptyResponseHeader, emptyResponseMessage).build();

	}
	
}
