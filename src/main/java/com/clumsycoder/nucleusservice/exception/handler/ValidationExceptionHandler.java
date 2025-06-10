package com.clumsycoder.nucleusservice.exception.handler;

import com.clumsycoder.controlshift.commons.nucleusservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.nucleusservice.constants.ErrorMessages;
import com.clumsycoder.nucleusservice.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationError(ValidationException e) {
        logger.error("Validation error occurred {}", e.getMessage(), e);
        return new ResponseEntity<>(new ApiError()
                .message(e.getMessage())
                .errorCode(ErrorCode.VALIDATION_ERROR.name()),
                HttpStatus.BAD_REQUEST);
    }

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
        ApiError error = new ApiError()
                .message(ErrorMessages.VALIDATION_ERROR)
                .errors(errors)
                .errorCode(ErrorCode.INTERNAL_ERROR.name());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}