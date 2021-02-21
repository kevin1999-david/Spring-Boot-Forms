package com.bolsaideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsaideas.springboot.form.app.editors.CapitalLetterNameEditor;
import com.bolsaideas.springboot.form.app.editors.CountryPropertyEditor;
import com.bolsaideas.springboot.form.app.editors.RolesEditor;
import com.bolsaideas.springboot.form.app.models.domain.Country;
import com.bolsaideas.springboot.form.app.models.domain.Role;
import com.bolsaideas.springboot.form.app.models.domain.User;
import com.bolsaideas.springboot.form.app.service.CountryService;
import com.bolsaideas.springboot.form.app.service.RoleService;
import com.bolsaideas.springboot.form.app.validation.ValidatorUser;

@Controller
@SessionAttributes("user") // persistir
public class FormController {

	@Autowired
	private ValidatorUser validator;
	@Autowired
	private CountryService countryService;
	@Autowired
	private CountryPropertyEditor countryEditor;
	@Autowired
	private RoleService roleService;

	@Autowired
	private RolesEditor roleEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);// Que no sea tolerante, que sea estricto como se quiere
		binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, false)); // For all fields
																										// // data
		binder.registerCustomEditor(String.class, "name", new CapitalLetterNameEditor());

		binder.registerCustomEditor(Country.class, "country", countryEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);

	}

	@ModelAttribute("countries")
	public List<String> countries() {
		return Arrays.asList("Spain", "Chile", "Brazil", "United States", "United Kingdom", "Ecuador");
	}

	@ModelAttribute("countriesMap")
	public Map<String, String> countriesMap() {
		Map<String, String> countries = new HashMap<String, String>();
		countries.put("SP", "Spain");
		countries.put("EC", "Ecuador");
		countries.put("CH", "Chile");
		countries.put("BZ", "Brazil");
		countries.put("US", "United States");
		countries.put("UK", "United Kingdom");
		countries.put("CO", "Colombia");

		return countries;
	}

	@ModelAttribute("countriesList")
	public List<Country> countriesList() {

		return countryService.list();
	}

	@ModelAttribute("rolesListString")
	public List<String> rolesListString() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	@ModelAttribute("rolesListMap")
	public Map<String, String> rolesListMap() {
		Map<String, String> roles = new HashMap<>();
		roles.put("ROLE_ADMIN", "Administrator");
		roles.put("ROLE_USER", "User");
		roles.put("ROLE_MODERATOR", "Moderator");

		return roles;
	}

	@ModelAttribute("genders")
	public List<String> gender() {
		return Arrays.asList("Woman", "Man", "Mix");
	}

	@ModelAttribute("rolesList")
	public List<Role> rolesList() {
		return roleService.list();
	}

	@GetMapping("/form")
	public String form(Model model) {

		model.addAttribute("title", "User Form");
		// Para que el usuario exista pero esta vacio al momento de hacer el get
		// para validar de tomar el valor cuando de verdad exista utilizar:
		// ${user?.username}
		User u = new User();
		u.setName("John");
		u.setLastname("Doe");
		u.setIdentifier("12.322.222-L");
		u.setEnable(true);
		u.setCountry(countryService.getCountryByName("United States"));
		u.setRoles(Arrays.asList(roleService.getRoleById(1), new Role(3, "Moderator", "ROLE_MODERATOR")));
		u.setSecretValue("Some secret text! **");
		model.addAttribute("user", u);
		return "form";
	}

	@PostMapping("/form") // @ModelAttribute("user")
	public String getFormData(@Valid User user, BindingResult result, Model model) {
		// validator.validate(user, result);
		// model.addAttribute("title", "New User!");
		if (result.hasErrors()) {
			/*
			 * Map<String, String> errors = new HashMap<>();
			 * result.getFieldErrors().forEach(err -> { errors.put(err.getField(),
			 * "The field ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage(
			 * ))); }); model.addAttribute("error", errors);
			 */
			model.addAttribute("title", "New User!");
			return "form";
		}
		// user.setIdentifier("123-nuevo");
		model.addAttribute("user", user);
		// status.setComplete(); //Delete user from the session, but data persist

		return "redirect:/view";
	}

	/*
	 * @PostMapping("/form") public String getFormData(Model
	 * model, @RequestParam(name = "username") String username,
	 * 
	 * @RequestParam String userpass, @RequestParam String useremail) { User user =
	 * new User(username, userpass, useremail); model.addAttribute("title",
	 * "New User!"); model.addAttribute("user", user); return "result"; }
	 */

	@GetMapping("view")
	public String view(@SessionAttribute(name = "user", required = false) User user, Model model, SessionStatus status) {
		
		if (user == null) {
			return "redirect:/form";
		}
		
		model.addAttribute("title", "New User!");

		status.setComplete();
		return "result";
	}
	
	

}
