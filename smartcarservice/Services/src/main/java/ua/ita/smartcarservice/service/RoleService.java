package ua.ita.smartcarservice.service;

import java.util.List;
import java.util.Optional;

import ua.ita.smartcarservice.entity.RoleEntity;

public interface RoleService {

	 void createRole(RoleEntity roleEntity);
		
		List<RoleEntity> findAll();
		
		RoleEntity findById(Long id);
		
		void deleteById(Long id, RoleEntity roleEntity);
		
		void updateRoleById(Long id, RoleEntity roleEntity);
		
		Optional<RoleEntity> findByName(String roleName);
	
}
