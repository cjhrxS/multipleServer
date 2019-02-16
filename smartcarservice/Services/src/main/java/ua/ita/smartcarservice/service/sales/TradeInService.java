package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.entity.sales.UserDealer;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;

import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
public interface TradeInService {

void createTradeIn(TradeIn tradeIn);
void deleteTradeIn(TradeIn tradeIn);
    void deleteTradeIn(Long id);
    void editTradeIn(TradeIn tradeIn);
 List<TradeIn> findall();

 List<TradeIn>findAllByDealerEntity(DealerEntity dealerEntity);

 List<TradeIn>findAllByUserDealer(UserDealer userDealer);

 List<TradeIn>findAllToSM(UserSaleManager userSaleManager);

}
