package com.clumsycoder.nucleusservice.exception.handler;

import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.nucleusservice.exception.EmailAlreadyUsedException;
import com.clumsycoder.nucleusservice.exception.NucleusServiceException;
import com.clumsycoder.nucleusservice.exception.UserCreateFailedException;
import com.clumsycoder.nucleusservice.exception.UserDeleteFailed;
import com.clumsycoder.nucleusservice.exception.UserNotFoundException;
import com.clumsycoder.nucleusservice.exception.UserUpdateFailedException;
import com.clumsycoder.nucleusservice.exception.UsernameAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.clumsycoder.controlshift.commons.nucleusservice.ErrorCode;

@RestControllerAdvice
public class NucleusServiceExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(NucleusServiceExceptionHandler.class);

    @ExceptionHandler(exception = EmailAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyUsedException(EmailAlreadyUsedException e) {
        logger.error("Email already in use {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.EMAIL_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(exception = UsernameAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleUsernameAlreadyUsedException(UsernameAlreadyUsedException e) {
        logger.error("Username already in use {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USERNAME_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(exception = UserCreateFailedException.class)
    public ResponseEntity<ApiError> handleUserCreationFailedException(UserCreateFailedException e) {
        logger.error("Failed to create the user {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_CREATE_FAILED.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(exception = UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException e) {
        logger.error("User data not found {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_NOT_FOUND.name()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(exception = UserUpdateFailedException.class)
    public ResponseEntity<ApiError> handleUserUpdateFailure(UserUpdateFailedException e) {
        logger.error("Failed to update the user profile {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_UPDATE_FAILED.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(exception = UserDeleteFailed.class)
    public ResponseEntity<ApiError> handleUserDeleteFailed(UserUpdateFailedException e) {
        logger.error("Failed to delete the user profile {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_DELETE_FAILED.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(exception = NucleusServiceException.class)
    public ResponseEntity<ApiError> handleNucleusServiceException(NucleusServiceException e) {
        logger.error("Unknown exception occurred in the service {}", e.getMessage(), e);
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}