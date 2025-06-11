package com.clumsycoder.nucleusservice.exception.handler;

import com.clumsycoder.controlshift.commons.nucleusservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.nucleusservice.exception.EmailAlreadyUsedException;
import com.clumsycoder.nucleusservice.exception.UserNotFoundException;
import com.clumsycoder.nucleusservice.exception.UserOperationException;
import com.clumsycoder.nucleusservice.exception.UsernameAlreadyUsedException;
import com.clumsycoder.nucleusservice.exception.base.NucleusServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class NucleusServiceExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(NucleusServiceExceptionHandler.class);

    @ExceptionHandler(exception = EmailAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyUsedException(EmailAlreadyUsedException e) {
        logger.error("Email already in use {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.EMAIL_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(exception = UsernameAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleUsernameAlreadyUsedException(UsernameAlreadyUsedException e) {
        logger.error("Username already in use {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.USERNAME_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(exception = UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException e) {
        logger.error("User data not found {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.USER_NOT_FOUND.name()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(exception = UserOperationException.class)
    public ResponseEntity<ApiError> handleUserOperationException(UserOperationException e) {
        logger.error("Error occurred when performing user related CRUD operation", e);
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(exception = NucleusServiceException.class)
    public ResponseEntity<ApiError> handleNucleusServiceException(NucleusServiceException e) {
        logger.error("Unknown exception occurred in the service {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}