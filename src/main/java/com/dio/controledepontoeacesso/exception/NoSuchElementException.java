package com.dio.controledepontoeacesso.exception;

import com.dio.controledepontoeacesso.util.MessageUtils;

public class NoSuchElementException extends RuntimeException {

    public NoSuchElementException() {
        super(MessageUtils.ELEMENT_NOT_FOUND);
    }
}
