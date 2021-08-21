package com.dio.controledepontoeacesso.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.dio.controledepontoeacesso.validator.UFValidator;


@Constraint(validatedBy = {UFValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface UF {

    String message() default "Estado inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
