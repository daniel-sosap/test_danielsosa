package com.dsp.restdsl.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//import javax.validation.Constraint;
//import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Anotación personalizada para validar si un campo es numérico.
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IsNumericValidator.class)
public @interface IsNumeric {

    /**
     * Mensaje de error que se muestra cuando la validación falla.
     *
     * @return el mensaje de error.
     */
    String message() default "El campo debe ser numérico.";  // Mensaje predeterminado

    /**
     * Define los grupos de validación a los que pertenece esta restricción.
     *
     * @return los grupos de validación.
     */
    Class<?>[] groups() default {};

    /**
     * Define los metadatos adicionales que se pueden asociar con la validación.
     *
     * @return la carga útil asociada a la validación.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Nombre del campo que debe ser numérico (generalmente utilizado para personalizar la validación).
     *
     * @return el nombre del campo.
     */
    String fieldName();
}
