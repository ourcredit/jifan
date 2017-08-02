package com.yrkj.mapper;

import com.yrkj.model.order.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by xuenianxiang on 2017/8/2.
 */
@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    int insertOrderProduct(Order order);

}
