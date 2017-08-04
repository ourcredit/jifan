package com.yrkj.mapper;

import com.yrkj.model.order.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/8/2.
 */
@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    int insertOrderProduct(Order order);

    Order selectOrder(Long id);

    List<Map> selectOrderProduct(Long order_id);
}
