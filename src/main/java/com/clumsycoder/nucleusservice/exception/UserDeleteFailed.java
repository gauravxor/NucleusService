package com.clumsycoder.nucleusservice.exception;

import com.clumsycoder.nucleusservice.constants.ErrorMessages;

public class UserDeleteFailed extends NucleusServiceException {
    public UserDeleteFailed() {
        super(ErrorMessages.USER_DELETE_FAILED);
    }

    public UserDeleteFailed(Throwable cause) {
        super(ErrorMessages.USER_DELETE_FAILED, cause);
    }

    public UserDeleteFailed(String errorMessage) {
        super(errorMessage);
    }

    public UserDeleteFailed(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}