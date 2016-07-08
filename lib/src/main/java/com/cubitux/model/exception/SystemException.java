package com.cubitux.model.exception;

/**
 * Created by pierre on 2016-07-02.
 */
public class SystemException extends Exception {
    Exception innerException;

    public SystemException(Exception e) {
        innerException = e;
    }
}
