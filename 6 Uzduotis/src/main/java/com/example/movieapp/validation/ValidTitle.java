package com.example.movieapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TitleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTitle {
    String message() default "Title must contain letter and spaces";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
