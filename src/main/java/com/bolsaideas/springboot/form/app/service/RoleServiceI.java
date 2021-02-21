package com.bolsaideas.springboot.form.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.models.domain.Role;

@Service
public class RoleServiceI implements RoleService {

	private List<Role> roles;

	public RoleServiceI() {
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1, "Administrator", "ROLE_ADMIN"));
		this.roles.add(new Role(2, "User", "ROLE_USER"));
		this.roles.add(new Role(3, "Moderator", "ROLE_MODERATOR"));
	}

	@Override
	public List<Role> list() {
		return roles;
	}

	@Override
	public Role getRoleById(Integer id) {
		Role roleFound = null;
		for (Role role : roles) {
			if (role.getId() == id) {
				roleFound = role;
				break;
			}
		}

		if (roleFound != null) {
			return roleFound;
		}
		return null;
	}

}
