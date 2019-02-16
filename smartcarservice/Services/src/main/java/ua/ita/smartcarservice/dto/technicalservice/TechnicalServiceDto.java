package ua.ita.smartcarservice.dto.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import java.util.Set;

@Data
public class TechnicalServiceDto {

    private Long stoId;

    private String name;

    private String address;

    //private Dealer dealer;


    private UserEntity technicalManager;

    @JsonIgnore//Works
    private Set<UserEntity> workerSet;
}
