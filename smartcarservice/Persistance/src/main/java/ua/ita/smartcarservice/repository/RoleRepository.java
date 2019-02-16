package ua.ita.smartcarservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import ua.ita.smartcarservice.entity.RoleEntity;
import ua.ita.smartcarservice.entity.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	Optional<RoleEntity> findByName(RoleName roleName);
	
	Optional<RoleEntity> findByName(String roleName);
	
	

}
