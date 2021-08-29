package com.dio.controledepontoeacesso.response;

public class UsuarioResponse extends Response {

    public static final String ENTITY_NAME = "User";

    public static final String ENTITY_NOT_FOUND = ENTITY_NAME.concat(" was not found");

    public static final String ENTITY_IS_REQUIRED = ENTITY_NAME.concat(" is required");

    public static final String JORNADA_TRABALHO_NOT_FOUND = JornadaTrabalhoResponse.ENTITY_NOT_FOUND;

    public static final String JORNADA_TRABALHO_IS_REQUIRED = JornadaTrabalhoResponse.ENTITY_IS_REQUIRED;

    public static final String CATEGORIA_USUARIO_NOT_FOUND = CategoriaUsuarioResponse.ENTITY_NOT_FOUND;

    public static final String CATEGORIA_USUARIO_IS_REQUIRED = CategoriaUsuarioResponse.ENTITY_IS_REQUIRED;

    public static final String EMPRESA_NOT_FOUND = EmpresaResponse.ENTITY_NOT_FOUND;

    public static final String EMPRESA_IS_REQUIRED = EmpresaResponse.ENTITY_IS_REQUIRED;

    public static final String NIVEL_ACESSO_NOT_FOUND = NivelAcessoResponse.ENTITY_NOT_FOUND;

    public static final String NIVEL_ACESSO_IS_REQUIRED = NivelAcessoResponse.ENTITY_IS_REQUIRED;
}