package com.dio.controledepontoeacesso.response;

public class LocalidadeResponse extends Response {

    public static final String ENTITY_NAME = "Local";

    public static final String ENTITY_NOT_FOUND = ENTITY_NAME.concat(" was not found");

    public static final String NIVEL_ACESSO_NOT_FOUND = NivelAcessoResponse.ENTITY_NOT_FOUND;

    public static final String NIVEL_ACESSO_IS_REQUIRED = NivelAcessoResponse.ENTITY_IS_REQUIRED;
}
