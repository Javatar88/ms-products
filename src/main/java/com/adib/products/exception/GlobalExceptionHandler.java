package com.adib.products.exception;

import com.adib.products.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author Mahmoud Ahmed Said
 * this class represent Exception handle ControllerAdvice when an exception is thrown
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * this is ExceptionHandler to handle productNotFoundException
     * @param exception
     * @param webRequest
     * @return ErrorResponse Entity
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException exception, WebRequest webRequest) {
        ErrorResponse response = new ErrorResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("The Requested product is Not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     *  this method is to validate the request data  and applies the validation on the DTO
     * @param MethodArgumentNotValidException
     * @param httpHeaders
     * @param HttpStatus
     * @param WebRequest
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(validationList.get(0));
        errorResponse.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, status);
    }





}
