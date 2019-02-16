package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.SaleManagerEntity;
import ua.ita.smartcarservice.entity.sales.UserDealer;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;

import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */

public interface SaleManagerService {

    void createSaleManager(DealerEntity dealerEntity,Long id);

    void createSaleManager(DealerEntity dealerEntity);


    List<SaleManagerEntity> findByUserDealer(UserDealer userDealer);
    List<SaleManagerEntity> findByUserDealer(DealerEntity dealerEntity);




    void deleteSalemanager(Long id);

    void editUserSaleManager(UserSaleManager userSaleManager);
    void editSalemanagerEntity(SaleManagerEntity saleManagerEntity);

    UserSaleManager findbyId(Long id);
    SaleManagerEntity findById(Long id);

    UserSaleManager findBySaleManagerEntity(SaleManagerEntity saleManagerEntity);

    SaleManagerEntity findByUserSaleManager(UserSaleManager userSaleManager);



}
