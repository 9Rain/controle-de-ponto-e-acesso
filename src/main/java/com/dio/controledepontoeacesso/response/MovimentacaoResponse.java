package com.dio.controledepontoeacesso.response;

public class MovimentacaoResponse extends Response {

    public static final String ENTITY_NAME = "Movement";

    public static final String ENTITY_NOT_FOUND = ENTITY_NAME.concat(" was not found");

    public static final String ENTITY_IS_REQUIRED = ENTITY_NAME.concat(" is required");

    public static final String CALENDARIO_NOT_FOUND = CalendarioResponse.ENTITY_NOT_FOUND;

    public static final String CALENDARIO_IS_REQUIRED = CalendarioResponse.ENTITY_IS_REQUIRED;

    public static final String OCORRENCIA_NOT_FOUND = OcorrenciaResponse.ENTITY_NOT_FOUND;

    public static final String OCORRENCIA_IS_REQUIRED = OcorrenciaResponse.ENTITY_IS_REQUIRED;
}
