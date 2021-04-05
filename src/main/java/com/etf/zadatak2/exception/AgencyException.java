package com.etf.zadatak2.exception;

/**
 *
 * @author Dusan
 */
public class AgencyException extends Exception{
    
    public AgencyException(String message) {
        super(message);
    }

    public AgencyException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
