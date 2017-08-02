package com.yrkj.service;

import com.yrkj.mapper.OrderMapper;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuenianxiang on 2017/8/2.
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public ActionResult createOrder(Order order){

        orderMapper.insertOrder(order);

        orderMapper.insertOrderProduct(order);

        return new ActionResult(true,null,"创建订单成功");

    }

}
