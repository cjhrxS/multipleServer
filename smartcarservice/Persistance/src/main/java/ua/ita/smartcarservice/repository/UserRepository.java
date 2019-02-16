package ua.ita.smartcarservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;
import ua.ita.smartcarservice.entity.RoleEntity;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

import javax.persistence.NamedNativeQuery;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(String username);
	
	boolean existsByUsername(String username);

	List<UserEntity> getByUserTechnicalServiceAndWorkersSkill(UserTechnicalService userTechnicalService,
															  WorkersSkill workersSkill);


	@Query("select distinct u from UserEntity as u left join WorkersSkill as w " +
			"on u.id = w.workerId left join UserTechnicalService as ut " +
			"on u.id = (select ut.userId from UserTechnicalService as ut " +
			"left join Car as c on ut.userId = c.user " +
			"where c.id = :id) where w.skill.name = :name")
	List<UserEntity> getByCarIdAndWorkersSkill(@Param("name") String name, @Param("id") Long id);

	List<UserEntity> getByUserTechnicalService(UserTechnicalService userTechnicalService);

	UserEntity getUserById(Long id);

	List<UserEntity> findAll();


    @Query(value = "select * from user as u left join user_roles as ur on u.id = ur.user_id left join role as r on ur.role_id = r.id where r.name = name", nativeQuery = true)
	List<UserEntity> getUserEntitiesByRoleName(@Param("name") String name);
}
