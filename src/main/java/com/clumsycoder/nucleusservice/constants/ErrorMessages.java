package com.clumsycoder.nucleusservice.constants;

/**
 * The error messages constants should be named exactly like the exceptions
 */
public class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String SERVICE_ERROR = "An internal error occurred in Nucleus Service.";
    public static final String EMAIL_ALREADY_USED = "Email address is already in use.";
    public static final String USER_DOES_NOT_EXISTS = "User does not exist.";
    public static final String USERNAME_USED = "Username is already taken.";
    public static final String VALIDATION_ERROR = "Input validation failed.";
    public static final String OPERATION_ERROR = "User operation failed.";
}