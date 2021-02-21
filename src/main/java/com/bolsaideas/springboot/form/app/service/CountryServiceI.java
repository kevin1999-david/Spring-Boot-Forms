package com.bolsaideas.springboot.form.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.models.domain.Country;

@Service
public class CountryServiceI implements CountryService {

	private List<Country> countryList;

	public CountryServiceI() {
		this.countryList = Arrays.asList(new Country(1, "EC", "Ecuador"), new Country(2, "CH", "Chile"),
				new Country(3, "CO", "Colombia"), new Country(4, "US", "United States"),
				new Country(5, "UK", "United Kingdom"));
	}

	@Override
	public List<Country> list() {

		return countryList;
	}

	@Override
	public Country getCountryById(Integer id) {
		Country countryFound = null;
		for (Country country : countryList) {
			if (country.getId() == id) {
				countryFound = country;
				break;
			}
		}
		return countryFound;
	}

	@Override
	public Country getCountryByName(String name) {
		Country countryFound = null;
		for (Country country : countryList) {
			if (country.getName().equals(name)) {
				countryFound = country;
				break;
			}
		}
		return countryFound;
	}

}
