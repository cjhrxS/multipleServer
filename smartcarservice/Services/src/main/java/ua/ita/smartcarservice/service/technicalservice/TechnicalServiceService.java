package ua.ita.smartcarservice.service.technicalservice;

import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;

public interface TechnicalServiceService {

    TechnicalServiceEntity getTechnicalServiceById(Long id);
    void createTechnicalService(String name, String address, Long userId);
    TechnicalServiceDto getTechnicalServiceDtoByUser(Long userId);
    void deleteTechnicalService(Long id);

    List<TechnicalServiceEntity> getAllTechnicalServices();
    List<TechnicalServiceDto> getAllTechnicalServicesDto();
    TechnicalServiceEntity getByTechnicalManager(UserEntity technicalManager);
    TechnicalServiceDto getTechnicalServiceDtoById(Long id);
    TechnicalServiceDto updateTechnicalService(TechnicalServiceEntity technicalService);
    TechnicalServiceDto updateTechnicalService(TechnicalServiceDto technicalServiceDto);

}
