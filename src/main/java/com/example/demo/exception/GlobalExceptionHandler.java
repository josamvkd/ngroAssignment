package com.example.demo.exception;


import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {
	 @ExceptionHandler({ CustomeException.class })
	    protected ResponseEntity<ApiErrorResponse> handleApiException(CustomeException ex) {
	        return new ResponseEntity<>(new ApiErrorResponse(ex.getStatus(), ex.getMessage(), Instant.now()), ex.getStatus());
	    }
}
