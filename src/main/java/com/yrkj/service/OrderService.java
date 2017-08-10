package com.yrkj.service;

import com.yrkj.mapper.OrderMapper;
import com.yrkj.mapper.UserMapper;
import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProduct;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.order.Order;
import com.yrkj.model.order.UserAchievement;
import com.yrkj.model.order.UserIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/2.
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

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

            //获取收货地址+邮费


            Order receive = userMapper.selectDefaultAddressPrice(order.getOpen_id());
            if (receive !=null){
                order.setCourier_cost(receive.getCourier_cost());
                order.setCity_id(receive.getCity_id());
                order.setProvince_id(receive.getProvince_id());
                order.setCity_name(receive.getCity_name());
                order.setProvince_name(receive.getProvince_name());
                order.setAddress(receive.getAddress());
                order.setReceiver(receive.getReceiver());
                order.setPhone(receive.getPhone());
            }

            //获取商品列表
            List list = orderMapper.selectOrderProduct(id);
            order.setList(list);
            return new ActionResult(false,order,"获取成功");
        } else {
            return new ActionResult(false,null,"获取失败");
        }
    }

    /**
     * 更新订单
     * @param order
     * @return
     */
    @Transactional
    public ActionResult updateOrder(Order order){
        if (orderMapper.updateOrder(order) > 0){
            return new ActionResult(true,null,"更新订单成功");

        } else {
            return new ActionResult(false,null,"更新订单失败");
        }
    }

    /**
     * 完成订单
     * @param order_num
     * @return
     */
    @Transactional
    public ActionResult finishOrder(String order_num){

        Date now = new Date();

        //查询订单
        Order order = orderMapper.selectOrderByNum(order_num);
        //更改为支付状态
        order.setOrder_state(1);
        //更新订单状态
        orderMapper.updateOrder(order);
        //查询订单涉及的单一商品
        List<UserCart> productList = orderMapper.selectProductNumberByOrderId(order.getId());

        for (UserCart cart:productList){
            UserProduct product = new UserProduct();
            product.setOpen_id(order.getOpen_id());
            product.setProduct_id(cart.getProduct_id());
            product.setCreate_time(now);

            if (orderMapper.selectUserProductExist(product) == 0){
                orderMapper.insertUserProduct(product);
            }
        }

        //查询本次获得的成就
        List<UserAchievement> achievementList = orderMapper.selectCurrentGetAchievement(order.getOpen_id());

        //插入成就
        for (UserAchievement achievement:achievementList){
            achievement.setOpen_id(order.getOpen_id());
            achievement.setCreate_time(now);
            orderMapper.insertUserAchievement(achievement);
        }

        //插入积分
        for (UserAchievement achievement:achievementList){
            UserIntegration integration = new UserIntegration();
            integration.setOpen_id(order.getOpen_id());
            integration.setCreate_time(now);
            integration.setIntegration_val(achievement.getIntegration());
            integration.setRemark("获得"+achievement.getAchievement_name());
            orderMapper.insertUserIntegration(integration);
        }

        return new ActionResult(true,null,"成功");
    }

}
