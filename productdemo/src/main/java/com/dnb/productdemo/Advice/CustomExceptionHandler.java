package com.dnb.productdemo.Advice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	// If we didnt metion any arguments we will be getting errors in the formatted
	// way using CustomExceptionHandler
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, Object> responsBody = new HashMap<>();
		responsBody.put("timestamp", LocalDate.now());
		responsBody.put("status", status.value());
		Stream<FieldError> errors = ex.getBindingResult().getFieldErrors().stream();
		Map<String, String> error = errors.collect(Collectors.toMap(a -> a.getField(), ex1 -> ex1.getDefaultMessage()));
		responsBody.put("errors", error);
		return new ResponseEntity<Object>(responsBody, headers, status);
	}

}
