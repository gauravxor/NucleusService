package com.clumsycoder.nucleusservice.exception;

import com.clumsycoder.nucleusservice.constants.ErrorMessages;

public class ValidationException extends NucleusServiceException {
    public ValidationException() {
        super(ErrorMessages.VALIDATION_ERROR);
    }

    public ValidationException(Throwable cause) {
        super(ErrorMessages.VALIDATION_ERROR, cause);
    }

    public ValidationException(String errorMessage) {
        super(errorMessage);
    }

    public ValidationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}