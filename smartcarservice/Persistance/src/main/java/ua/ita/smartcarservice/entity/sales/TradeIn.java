package ua.ita.smartcarservice.entity.sales;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by 1 on 10.02.2019.
 */

@Data
@Entity
@Table(name = "TradeIn")
public class TradeIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String vinNewCar;

    @Column(length = 100, nullable = false, unique = true)
    private String vinUsedCar;

    @Column(length = 100, nullable = false, unique = true)
    private Long idUser;

    @Column(length = 100, nullable = false, unique = true)
    private Long idDealer;

    @Column(length = 100, nullable = false, unique = true)
    private String isactive;


    @ManyToOne
    @JoinColumn(name = "dealerEntity",nullable = false)
    private DealerEntity dealerEntity;





}
