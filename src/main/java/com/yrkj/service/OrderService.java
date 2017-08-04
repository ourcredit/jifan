package com.yrkj.service;

import com.yrkj.mapper.OrderMapper;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        return new ActionResult(true,order,"创建订单成功");

    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    public ActionResult getOrderById(Long id){
        Order order = orderMapper.selectOrder(id);
        if (order != null){
            List list = orderMapper.selectOrderProduct(id);
            order.setList(list);
            return new ActionResult(false,order,"获取成功");
        } else {
            return new ActionResult(false,null,"获取失败");
        }
    }

    @Transactional
    public ActionResult updateOrder(Order order){

        return new ActionResult(true,null,"更新订单成功");

    }

}
