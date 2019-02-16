package ua.ita.smartcarservice.entity.sales;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;

/**
 * Created by 1 on 10.02.2019.
 */
@Data
@Entity
@Table(name = "users_salemanager")
public class UserSaleManager {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;
//
//    @ManyToOne
//    @JoinColumn(name = "dealer_id",nullable = false)
//    private DealerEntity dealerEntity;

    @ManyToOne
    @JoinColumn(name = "salemanager_id",nullable = false)
     private SaleManagerEntity saleManagerEntity;

    public UserSaleManager(UserEntity userId, SaleManagerEntity saleManagerEntity) {
        this.userId = userId;
        this.saleManagerEntity = saleManagerEntity;
    }
}
