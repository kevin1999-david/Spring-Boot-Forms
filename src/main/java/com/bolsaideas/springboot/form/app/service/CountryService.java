package com.bolsaideas.springboot.form.app.service;

import java.util.List;

import com.bolsaideas.springboot.form.app.models.domain.Country;

public interface CountryService {
	public List<Country> list();
	public Country getCountryById(Integer id);
	public Country getCountryByName(String name);
}
