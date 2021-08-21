package com.dio.controledepontoeacesso.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UFValidator implements ConstraintValidator<com.dio.controledepontoeacesso.annotation.UF, String> {

    private final String UF[] = {
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
            "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    };

    @Override
    public void initialize(com.dio.controledepontoeacesso.annotation.UF constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.isEmpty() || isUf(value);
    }

    private boolean isUf(String value) { return Arrays.stream(this.UF).anyMatch(uf -> uf.equals(value)); }
}
