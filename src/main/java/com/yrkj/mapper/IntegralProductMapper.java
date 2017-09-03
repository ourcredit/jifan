package com.yrkj.mapper;

import com.yrkj.model.Integral.*;
import com.yrkj.model.core.BaseModel;
import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaohejing on 2017/8/1.
 */
@Mapper
public interface IntegralProductMapper {
    int insert(IntegralProduct product);

    int update(IntegralProduct product);

    int delete(IdsModel model);

    List<IntegralProduct> selectAll(IntegralSearch model);

    List<Map> selectLotteryList();

    IntegralProduct selectById(Long id);
    //批量更新状态
    int updateStatus(ChangeStatusModel model);
    List<IntegralDto> GetIntegralProducts(SearchModel input);
    IntegralProduct GetIntegralById(Long id);
    int InsertOrder(IntegralOrder input);

    List<IntegralOrder> selectOrders(IntegralSearch model);
    int UpdateCourier (CourierInput input);
    int selectIntegralProductLess(Long productId);
}
