package com.clumsycoder.nucleusservice.exception;

import com.clumsycoder.nucleusservice.constants.ErrorMessages;

public class UserNotFoundException extends NucleusServiceException {
    public UserNotFoundException() {
        super(ErrorMessages.USER_DOES_NOT_EXISTS);
    }

    public UserNotFoundException(Throwable cause) {
        super(ErrorMessages.USER_DOES_NOT_EXISTS, cause);
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}