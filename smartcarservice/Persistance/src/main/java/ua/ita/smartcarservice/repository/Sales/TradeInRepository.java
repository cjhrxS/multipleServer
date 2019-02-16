package ua.ita.smartcarservice.repository.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;

import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
public interface TradeInRepository extends JpaRepository<TradeIn,Long> {

//    @Override
//    public List<TradeIn> findAllByDealerEntity(DealerEntity dealerEntity) {
//        return null;
//    }
//
//    @Override
//    public List<TradeIn> findAllByUserDealer(UserDealer userDealer) {
//        return null;
//    }
//
//    @Override
//    public List<TradeIn> findAllToSM(UserSaleManager userSaleManager) {
//        return null;
//    }


    List<TradeIn>findAllByDealerEntity(DealerEntity dealerEntity);




}
