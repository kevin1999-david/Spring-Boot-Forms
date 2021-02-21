package com.bolsaideas.springboot.form.app.service;

import java.util.List;

import com.bolsaideas.springboot.form.app.models.domain.Role;

public interface RoleService {
	
	public List<Role> list();
	public Role getRoleById(Integer id);

}
