package br.senaisc.lab365.itacorubi.semana07.climed.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UuidValidator implements ConstraintValidator<ValidUuid, java.util.UUID> {
    private final String regex = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";

    @Override
    public void initialize(ValidUuid validUuid) {
        ConstraintValidator.super.initialize(validUuid);
    }
    /*@Override
    public void initialize(UUID uuid) {}*/

    @Override
    public boolean isValid(java.util.UUID uuid, ConstraintValidatorContext constraintValidatorContext) {
        return uuid.toString().matches(this.regex);
    }
}
