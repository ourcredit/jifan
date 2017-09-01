package com.yrkj.mapper;

import com.yrkj.model.User.UserTravels;
import com.yrkj.model.order.UserAchievement;
import com.yrkj.model.travels.UserTravelInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/8/19.
 */
@Mapper
public interface UserTravelsMapper {

    List<Map> selectTravels();

    List<Map> selectUserHasTravels(String open_id);

    UserTravels selectUserTravels(UserTravels travels);

    int insertUserTravels(UserTravels travels);
    List<UserTravels> selectTodayTravel(UserTravelInput input);

    List<UserAchievement> selectCurrentTravelsAchievement(String open_id);
}
