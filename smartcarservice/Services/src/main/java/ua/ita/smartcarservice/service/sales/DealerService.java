package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.sales.*;

/**
 * Created by 1 on 10.02.2019.
 */

public interface DealerService {

    void createDealerEntity(String name,String address,String edr,Long Id);

    DealerEntity getBySaleManager(UserSaleManager userSaleManager);
    DealerEntity getByUserDealer(UserDealer userDealer);
    DealerEntity getByUserDealerId(Long UserDealerId);

    DealerEntity getByTechnicalServiceEntityId(Long id);
//    DealerEntity getByUserTechnicalService(Long id);

    DealerEntity getBySaleManagerEntity(SaleManagerEntity saleManagerEntity);
    DealerEntity getByUserSaleManager(UserSaleManager userSaleManager);

    DealerEntity getByTradeIn(TradeIn tradeIn);

    void editDealerEntity(DealerEntity dealerEntity);
    void editUserDealer(UserDealer userDealer);






}
