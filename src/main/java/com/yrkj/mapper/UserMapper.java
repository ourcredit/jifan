package com.yrkj.mapper;

import com.yrkj.model.User.User;
import com.yrkj.model.User.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/10.
 */
@Mapper
public interface UserMapper {

    int insert(String open_id);

    User selectByOpenId(String open_id);

    int update(User user);

    //收货地址管理
    int insertUserAddress(UserAddress address);

    List<UserAddress> selectUserAddressList(String open_id);

    UserAddress selectUserAddressInfo(Long id);

    int updateUserAddress(UserAddress address);

    int updateUserAddressNotDefault(String open_id);

    int updateUserAddressDefault(UserAddress address);

    int deleteUserAddress(Long id);
}
