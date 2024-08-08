package com.curso_api.exception;

import com.curso_api.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception e){
        ApiError apiError = ApiError.builder()
                .backendMessage(e.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .message("Error interno en el servidor")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e){
        ApiError apiError = ApiError.builder()
                .backendMessage(e.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .message("Parametro invalido en la peticion" + e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}
