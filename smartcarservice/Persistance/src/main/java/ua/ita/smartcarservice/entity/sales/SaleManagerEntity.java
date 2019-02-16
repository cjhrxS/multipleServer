package ua.ita.smartcarservice.entity.sales;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
@Data
@Entity
@Table(name = "salemanager")
public class SaleManagerEntity {

    public SaleManagerEntity() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salemanagerId;



    @OneToMany(mappedBy = "saleManagerEntity")
    private List<UserSaleManager> userSaleManagers;


    @ManyToOne
    @JoinColumn(name = "dealerEntity",nullable = false)
    private DealerEntity dealerEntity;

    public SaleManagerEntity(List<UserSaleManager> userSaleManagers, DealerEntity dealerEntity) {
        this.userSaleManagers = userSaleManagers;
        this.dealerEntity = dealerEntity;
    }

    public SaleManagerEntity(DealerEntity dealerEntity) {
        this.dealerEntity = dealerEntity;
    }
}
