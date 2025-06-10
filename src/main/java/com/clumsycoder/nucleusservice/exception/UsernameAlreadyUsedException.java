package com.clumsycoder.nucleusservice.exception;

import com.clumsycoder.nucleusservice.constants.ErrorMessages;

public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException() {
        super(ErrorMessages.USERNAME_USED);
    }

    public UsernameAlreadyUsedException(Throwable cause) {
        super(ErrorMessages.USERNAME_USED, cause);
    }

    public UsernameAlreadyUsedException(String errorMessage) {
        super(errorMessage);
    }

    public UsernameAlreadyUsedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}