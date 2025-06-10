package com.clumsycoder.nucleusservice.constants;

/**
 * The error messages constants should be named exactly like the exceptions
 */
public class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String SERVICE_ERROR = "Something went wrong in Nucleus Service.";
    public static final String EMAIL_ALREADY_USED = "Email is already used";
    public static final String USER_DOES_NOT_EXISTS = "User does not exists.";
    public static final String USER_UPDATE_FAILED = "Failed to update the user details";
    public static final String USER_DELETE_FAILED = "Failed to delete the user data";
    public static final String USERNAME_USED = "Username not available for use";
    public static final String USER_CREATE_FAILED = "Failed to create the user";
    public static final String VALIDATION_ERROR = "Validation error";
}