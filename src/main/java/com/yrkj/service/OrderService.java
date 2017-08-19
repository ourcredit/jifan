package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.OrderMapper;
import com.yrkj.mapper.UserMapper;
import com.yrkj.model.Integral.CourierInput;
import com.yrkj.model.Integral.IntegralSearch;
import com.yrkj.model.User.User;
import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProduct;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.order.Order;
import com.yrkj.model.order.UserAchievement;
import com.yrkj.model.order.UserIntegration;
import com.yrkj.model.order.WXOrderSearch;
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

        //删除购物车相关物品
        orderMapper.deleteUserCart(order);

        return new ActionResult(true,order,"创建订单成功");

    }

    /**
     * 获取微信订单列表
     * @param model
     * @return
     */
    public PageModel getWxOrderList(WXOrderSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        List list = orderMapper.selectWxOrderList(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    /**
     * 获取积分订单列表
     * @param model
     * @return
     */
    public PageModel getIntegralOrderList(WXOrderSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        List list = orderMapper.selectIntegralOrderList(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    /**
     * 获取微信订单列表
     * @param model
     * @return
     */
    public PageModel GetOrdersList(IntegralSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());
        List list = orderMapper.GetAllOrders(model);
        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }
    /**
     * 获取订单详情
     * @param orderNum
     * @return
     */
    public ActionResult GetOrderDetail(String orderNum){
        List list = orderMapper.GetOrderDetail(orderNum);
        if (list.size() > 0){
            return new ActionResult(true,list,"获取成功");
        }else {
            return new ActionResult(true,list,"暂无数据");
        }
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
            if (order.getOrder_state() == 0){//判断是否生成了微信支付订单
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
     * 更新快递信息
     * @param input
     * @return
     */
    public  ActionResult UpdateCourier(CourierInput input){
        if (orderMapper.UpdateCourier(input) == 1){
            return new ActionResult(true,null,"更新成功");
        }
        return new ActionResult(false,null,"更新失败");
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

        Integer achievement_val = userMapper.selectUserAchievementVal(order.getOpen_id());

        Integer integration_val = userMapper.selectUserIntegrationVal(order.getOpen_id());

        Integer badge = userMapper.selectUserBadge(order.getOpen_id());

        User user = new User();
        user.setAchievement_val(achievement_val);
        user.setIntegration_val(integration_val);
        user.setBadge_count(badge);
        //更新成就 积分 勋章数
        userMapper.updateUserVal(user);

        return new ActionResult(true,null,"成功");
    }

}
