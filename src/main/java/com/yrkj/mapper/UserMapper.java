package com.yrkj.mapper;

import com.yrkj.model.User.User;
import com.yrkj.model.User.UserAddress;
import com.yrkj.model.order.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/7/10.
 */
@Mapper
public interface UserMapper {

    int insert(String open_id);

    User selectByOpenId(String open_id);

    int update(User user);

    int updateUserVal(User user);

    //收货地址管理
    int insertUserAddress(UserAddress address);

    List<UserAddress> selectUserAddressList(String open_id);

    UserAddress selectUserAddressInfo(Long id);

    int updateUserAddress(UserAddress address);

    int updateUserAddressNotDefault(String open_id);

    int updateUserAddressDefault(UserAddress address);

    int deleteUserAddress(Long id);

    //获取某用户默认收货地址+邮费
    Order selectDefaultAddressPrice(String open_id);

    //查询用户 成就  积分  勋章
    Integer selectUserIntegrationVal(String open_id);

    Integer selectUserAchievementVal(String open_id);

    Integer selectUserBadge(String open_id);

    //轮播图+banner
    List<Map> selectShuffling(Integer cate);
}
