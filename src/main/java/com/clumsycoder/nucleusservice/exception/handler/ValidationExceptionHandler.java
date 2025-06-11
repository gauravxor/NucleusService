package com.clumsycoder.nucleusservice.exception.handler;

import com.clumsycoder.controlshift.commons.nucleusservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.nucleusservice.constants.ErrorMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(1)
public class ValidationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    /**
     * This handler's response data is to be consumed by the external public clients only
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException e) {
        logger.error("Validation constraint failure occurred {}", e.getMessage(), e);
        Map<String, Object> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(ErrorMessages.VALIDATION_ERROR)
                        .errors(errors)
                        .errorCode(ErrorCode.VALIDATION_ERROR.name()),
                HttpStatus.BAD_REQUEST
        );
    }
}