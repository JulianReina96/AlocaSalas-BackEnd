package com.AlocaSalas.manager.models.dto;

import com.AlocaSalas.manager.models.Role;

public record RoleDto(Long id, String role) {
	
	public RoleDto(Role role) {
		this(role.getId(), role.getRole());
	}

}
