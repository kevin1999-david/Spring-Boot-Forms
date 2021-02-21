package com.bolsaideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegexIdentifierValidator implements ConstraintValidator<RegexIdentifier, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value.matches("[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][a-zA-Z]{1}")) {
			return true;
		}
		return false;
	}

}
