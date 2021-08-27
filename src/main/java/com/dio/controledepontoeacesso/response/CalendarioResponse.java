package com.dio.controledepontoeacesso.response;

public class CalendarioResponse extends Response {

    public static final String ENTITY_NAME = "Calendar";

    public static final String ENTITY_NOT_FOUND = ENTITY_NAME.concat(" was not found");

    public static final String TIPO_DATA_NOT_FOUND = TipoDataResponse.ENTITY_NOT_FOUND;

    public static final String TIPO_DATA_IS_REQUIRED = TipoDataResponse.ENTITY_IS_REQUIRED;
}
