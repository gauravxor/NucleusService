package com.clumsycoder.nucleusservice.exception;

import com.clumsycoder.nucleusservice.constants.ErrorMessages;

/**
 * Base exception class for all the exceptions raised in the nucleus service
 */
public class NucleusServiceException extends RuntimeException {
    public NucleusServiceException() {
        super(ErrorMessages.SERVICE_ERROR);
    }

    public NucleusServiceException(Throwable cause) {
        super(ErrorMessages.SERVICE_ERROR, cause);
    }

    public NucleusServiceException(String errorMessage) {
        super(errorMessage);
    }

    public NucleusServiceException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}