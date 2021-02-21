package com.bolsaideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsaideas.springboot.form.app.service.CountryService;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private CountryService service;

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		try {
			this.setValue(service.getCountryById(Integer.parseInt(id.trim())));
		} catch (NumberFormatException e) {
			this.setValue(null);
		}
	}
}
