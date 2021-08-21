package com.dio.controledepontoeacesso.exception;

import com.dio.controledepontoeacesso.util.MessageUtils;

public class RelationshipNotFoundException extends RuntimeException {
    public RelationshipNotFoundException() {
        super(MessageUtils.NO_RELATIONSHIP_FOUND);
    }
}

