package com.clumsycoder.nucleusservice.exception;

import com.clumsycoder.nucleusservice.constants.ErrorMessages;
import com.clumsycoder.nucleusservice.exception.base.NucleusServiceException;

public class UserOperationException extends NucleusServiceException {
    public UserOperationException() {
        super(ErrorMessages.OPERATION_ERROR);
    }

    public UserOperationException(Throwable cause) {
        super(ErrorMessages.OPERATION_ERROR, cause);
    }

    public UserOperationException(String errorMessage) {
        super(errorMessage);
    }

    public UserOperationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}