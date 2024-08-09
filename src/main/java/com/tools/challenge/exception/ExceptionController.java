package com.tools.challenge.exception;


import com.tools.challenge.models.error.ErrorResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> mensagensDeErro = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse erroResponse = new ErrorResponse(
                HttpStatus.PRECONDITION_FAILED.value(),
                mensagensDeErro
        );

        return new ResponseEntity<>(erroResponse, HttpStatus.PRECONDITION_FAILED);
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse erroResponse = new ErrorResponse(
                HttpStatus.PRECONDITION_FAILED.value(),
                List.of(ex.getMessage())
        );

        return new ResponseEntity<>(erroResponse, HttpStatus.PRECONDITION_FAILED);
    }
}
