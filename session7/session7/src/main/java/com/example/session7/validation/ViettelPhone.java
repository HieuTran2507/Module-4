package com.example.session7.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ViettelPhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViettelPhone {

    String message() default "Số điện thoại không phải nhà mạng Viettel";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
