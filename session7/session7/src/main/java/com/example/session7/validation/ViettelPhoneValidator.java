package com.example.session7.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ViettelPhoneValidator implements ConstraintValidator<ViettelPhone,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // null để @NotBlank / @NotNull xử lý
        if (value == null) {
            return true;
        }

        return value.startsWith("086")
                || value.startsWith("096")
                || value.startsWith("097")
                || value.startsWith("098")
                || value.startsWith("032");
    }
}
