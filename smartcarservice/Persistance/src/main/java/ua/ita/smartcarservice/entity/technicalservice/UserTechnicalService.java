package ua.ita.smartcarservice.entity.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users_service")
public class UserTechnicalService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "technical_service_id", nullable = false)
    private TechnicalServiceEntity technicalServiceId;

    public UserTechnicalService() {

    }

    public UserTechnicalService(UserEntity userId, TechnicalServiceEntity technicalServiceId) {
        this.userId = userId;
        this.technicalServiceId = technicalServiceId;
    }
}
