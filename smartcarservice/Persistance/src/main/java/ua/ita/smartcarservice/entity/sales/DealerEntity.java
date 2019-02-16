package ua.ita.smartcarservice.entity.sales;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
@Data
@Entity
@Table(name = "dealer")

public class DealerEntity {

    public DealerEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealerId;

    @Column(length = 100, nullable = false, unique = false)
    private String name;

    @Column(length = 100, nullable = false, unique = false)
    private String address;

    @Column(length = 100, nullable = false, unique = true)
    private String edr;

    @OneToMany(mappedBy = "dealerEntity")
    private List<UserDealer>userDealers;


         @OneToMany(mappedBy = "dealerEntity")
    private List<SaleManagerEntity>saleManagerEntities;


    @OneToMany(mappedBy = "dealerEntity")
    private List<TradeIn>tradeIns;

    @OneToMany(mappedBy = "dealerEntity")
     private List<TechnicalServiceEntity>technicalServiceEntities;


    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEdr() {
        return edr;
    }

    public void setEdr(String edr) {
        this.edr = edr;
    }

    public List<UserDealer> getUserDealers() {
        return userDealers;
    }

    public void setUserDealers(List<UserDealer> userDealers) {
        this.userDealers = userDealers;
    }

    public List<SaleManagerEntity> getSaleManagerEntities() {
        return saleManagerEntities;
    }

    public void setSaleManagerEntities(List<SaleManagerEntity> saleManagerEntities) {
        this.saleManagerEntities = saleManagerEntities;
    }

    public List<TradeIn> getTradeIns() {
        return tradeIns;
    }

    public void setTradeIns(List<TradeIn> tradeIns) {
        this.tradeIns = tradeIns;
    }

    public List<TechnicalServiceEntity> getTechnicalServiceEntities() {
        return technicalServiceEntities;
    }

    public void setTechnicalServiceEntities(List<TechnicalServiceEntity> technicalServiceEntities) {
        this.technicalServiceEntities = technicalServiceEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
