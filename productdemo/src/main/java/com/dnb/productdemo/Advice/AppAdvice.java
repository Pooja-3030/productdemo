package com.dnb.productdemo.Advice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.dnb.productdemo.exceptions.IdNotFoundException;
@ControllerAdvice
public class AppAdvice {
	@ExceptionHandler(IdNotFoundException.class)
	 public ResponseEntity<?> invalidNameException(IdNotFoundException e){
		Map<String, String> map= new HashMap<>();
		map.put("message", "not found");
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String,String>> handleException(HttpRequestMethodNotSupportedException e)throws IOException
	{
		String provided = e.getMethod();
		List<String> supported= List.of(e.getSupportedMethods());
		String error = provided +"is not one of he supported http mehods("+String.join(",",supported)+")";
		Map<String,String> errorResponse = Map.of("error", error,"message",e.getLocalizedMessage(),"status",HttpStatus.METHOD_NOT_ALLOWED.toString());
		return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
		
	}

}
