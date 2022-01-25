package com.alkemy.desafioDisney.Exception;

@SuppressWarnings("serial")
public class ParamNotFound extends Throwable {
    public ParamNotFound(String error) {
        super(error);
    }
}
