package com.yrkj.mapper;

import com.yrkj.model.Integral.CourierInput;
import com.yrkj.model.Integral.IntegralSearch;
import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProduct;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.order.*;
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

    int deleteUserCart(Order order);

    int updateOrder(Order order);

    Order selectOrder(Long id);

    //获取微信用户订单列表
    List<Map> selectWxOrderList(WXOrderSearch model);

    Order selectOrderByNum(String order_num);

    List<Map> selectOrderProduct(Long order_id);

    List<UserCart> selectProductNumberByOrderId(Long id);

    //插入用户商品关系
    int insertUserProduct(UserProduct userProduct);

    //插入用户商品是否存在
    int selectUserProductExist(UserProduct userProduct);

    //获取本次获得成就
    List<UserAchievement> selectCurrentGetAchievement(String open_id);

    //插入用户成就
    int insertUserAchievement(UserAchievement achievement);

    //插入用户积分
    int insertUserIntegration(UserIntegration integration);
    //分页获取订单
    List<Order> GetAllOrders(IntegralSearch input);
    //更新快递信息
    int UpdateCourier (CourierInput input);
    List<OrderDto> GetOrderDetail (String orderNum);

}
