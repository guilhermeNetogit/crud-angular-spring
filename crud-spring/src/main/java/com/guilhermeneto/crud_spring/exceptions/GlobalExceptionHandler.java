package com.guilhermeneto.crud_spring.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private MessageSource messageSource;

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ResponseError responseError(String message, HttpStatus statusCode) {
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error");
        responseError.setError(message);
        responseError.setStatusCode(statusCode.value());
        return responseError;
    }

    @ExceptionHandler(RecordNotFound.class)
    private ResponseEntity<Object> handleGeneral(RecordNotFound e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, headers(), HttpStatus.NOT_FOUND); // Mude para isso
    }

    @ExceptionHandler({ BusinessException.class })
    private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(error, headers(), HttpStatus.CONFLICT); // Mude para isso
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        ResponseError error = responseError("Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error, headers(), HttpStatus.INTERNAL_SERVER_ERROR); // Mude para isso
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .reduce("", (acc, error) -> acc + error + "\n");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(error -> error.getPropertyPath() + " " + error.getMessage())
                .reduce("", (acc, error) -> acc + error + "\n");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (ex != null && ex.getRequiredType() != null) {
            String type = ex.getRequiredType().getName();
            String[] typeParts = type.split("\\.");
            String typeName = typeParts[typeParts.length - 1];
            return ex.getName() + " should be of type " + typeName;
        }
        return "Argument type not valid";
    }
}
