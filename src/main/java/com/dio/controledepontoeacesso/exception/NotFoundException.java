package com.dio.controledepontoeacesso.exception;

import com.dio.controledepontoeacesso.util.MessageUtils;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super(MessageUtils.NO_RECORDS_FOUND);
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
