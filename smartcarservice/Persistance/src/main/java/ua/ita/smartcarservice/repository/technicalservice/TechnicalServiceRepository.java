package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

@Repository
public interface TechnicalServiceRepository extends JpaRepository<TechnicalServiceEntity, Long> {
    TechnicalServiceEntity getByTechnicalServiceId(Long id);

    @Query(value = "select ts.technical_service_id, ts.name, ts.address, ts.dealer_entity from technical_services as ts " +
            "left join users_service as us on ts.technical_service_id = us.technical_service_id " +
            "left join user as u on us.user_id = u.id where u.id = :id", nativeQuery = true)
    TechnicalServiceEntity getTechnicalServiceEntitysByUser(@Param("id") Long id);

    //void deleteByTechnicalServiceId(L)
}
