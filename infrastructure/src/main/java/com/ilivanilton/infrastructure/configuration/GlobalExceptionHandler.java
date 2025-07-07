package com.ilivanilton.infrastructure.configuration;

import com.ilivanilton.domain.exceptions.DomainException;
import com.ilivanilton.domain.exceptions.NotFoundException;
import com.ilivanilton.domain.validation.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException ex) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(final RuntimeException ex) {
        return ResponseEntity.unprocessableEntity().body(ApiRuntimeError.from(ex));
    }

    record ApiRuntimeError(String msg){
        static ApiRuntimeError from(RuntimeException ex) {
            return new ApiRuntimeError(ex.getMessage());
        }
    }

    record ApiError(String message, List<Error> errors) {
        static ApiError from(final DomainException ex) {
            return new ApiError(ex.getMessage(), ex.getErrors());
        }
    }
}
