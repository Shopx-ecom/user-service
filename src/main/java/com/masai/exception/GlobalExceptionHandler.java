package com.masai.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFound(NotFoundException pnf,WebRequest wr){
		return buildResponse(HttpStatus.NOT_FOUND,pnf.getMessage());
	}

    @ExceptionHandler(AuthException.class)
	public ResponseEntity<?> authException(AuthException pnf,WebRequest wr){
		return buildResponse(HttpStatus.FORBIDDEN,pnf.getMessage());
	}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleGenericRuntimeException(RuntimeException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }



    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        if (message == null) {
            errorResponse.put("message", "Something went wrong!");
        }
        errorResponse.put("status", status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

}
