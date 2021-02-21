package com.bolsaideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsaideas.springboot.form.app.models.domain.User;

@Component
public class ValidatorUser implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// Para asegurar que se esta validando la clase User
		// To ensure that the User class is being validated
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		/* User user = (User) target; */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.user.name");

		/*
		 * if (!user.getIdentifier().matches(
		 * "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][a-zA-Z]{1}")) {
		 * errors.rejectValue("identifier", "Pattern.user.identifier"); }
		 */
		/*
		 * if (user.getName().isEmpty()) { errors.rejectValue("name",
		 * "NotEmpty.user.name"); }
		 */
	}

}
