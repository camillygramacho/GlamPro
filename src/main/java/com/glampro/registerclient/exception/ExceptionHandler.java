package com.glampro.registerclient.exception;

import java.io.Serial;

public class ExceptionHandler extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public ExceptionHandler(String message){
        super(message);
    }

}
