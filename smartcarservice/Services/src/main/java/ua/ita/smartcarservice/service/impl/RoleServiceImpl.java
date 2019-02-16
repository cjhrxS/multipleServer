package ua.ita.smartcarservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.ita.smartcarservice.entity.RoleEntity;
import ua.ita.smartcarservice.repository.RoleRepository;
import ua.ita.smartcarservice.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void createRole(RoleEntity roleEntity) {
		roleRepository.save(roleEntity);
	}

	@Override
	public List<RoleEntity> findAll() {
		List<RoleEntity> roles = roleRepository.findAll();
		return roles;
	}

	@Override
	public RoleEntity findById(Long id) {
		RoleEntity role = roleRepository.findById(id).get();
		return role;
	}

	@Override
	public void deleteById(Long id, RoleEntity roleEntity) {
		roleEntity.setId(id);
		roleRepository.save(roleEntity);
		
	}

	@Override
	public void updateRoleById(Long id, RoleEntity roleEntity) {
		
		roleEntity.setId(id);
		roleRepository.save(roleEntity);
		
	}

	@Override
	public Optional<RoleEntity> findByName(String roleName) {
		Optional<RoleEntity> roleEntity = roleRepository.findByName(roleName);
		return roleEntity;
	}

}
