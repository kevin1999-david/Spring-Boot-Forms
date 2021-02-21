package com.bolsaideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsaideas.springboot.form.app.models.domain.Role;
import com.bolsaideas.springboot.form.app.service.RoleService;

@Component
public class RolesEditor extends PropertyEditorSupport {
	@Autowired
	private RoleService roleService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
	
		try {
			Integer id = Integer.parseInt(text.trim());
			Role roleFound = roleService.getRoleById(id);
			this.setValue(roleFound);
			
		} catch (NumberFormatException e) {
			this.setValue(null);
		}
		
	}

}

