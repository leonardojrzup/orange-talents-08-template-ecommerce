package br.com.leonardo.mercadolivre.annotations;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {
    String message() default "Ja existe um campo com o mesmo valor inserido no banco de dados";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    //valor padrão caso não for passado nenhum valor na variavel
    String value() default "";

    String fieldName();
    Class<?> domainClass();

}