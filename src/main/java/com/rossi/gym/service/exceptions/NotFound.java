package com.rossi.gym.service.exceptions;

public class NotFound extends RuntimeException{

    public NotFound(Object id) {
        super("ID não encontrado! ID = " + id);
    }
}
