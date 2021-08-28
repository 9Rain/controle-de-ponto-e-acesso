package com.dio.controledepontoeacesso.response;

public class BancoHorasResponse extends Response {

    public static final String ENTITY_NAME = "Additional hour";

    public static final String ENTITY_NOT_FOUND = ENTITY_NAME.concat(" was not found");

    public static final String MOVIMENTACAO_NOT_FOUND = MovimentacaoResponse.ENTITY_NOT_FOUND;

    public static final String MOVIMENTACAO_IS_REQUIRED = MovimentacaoResponse.ENTITY_IS_REQUIRED;
}