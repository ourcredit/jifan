package com.yrkj.mapper;

import com.yrkj.model.User.UserTravels;
import com.yrkj.model.order.UserAchievement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/8/19.
 */
@Mapper
public interface UserTravelsMapper {

    List<Map> selectTravels();

    UserTravels selectUserTravels(UserTravels travels);

    int insertUserTravels(UserTravels travels);

    List<UserAchievement> selectCurrentTravelsAchievement(String open_id);
}
