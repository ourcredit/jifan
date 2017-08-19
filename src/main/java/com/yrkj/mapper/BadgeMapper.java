package com.yrkj.mapper;

import com.yrkj.model.badge.BadgeSearch;
import com.yrkj.model.badge.UserAchievementSearch;
import com.yrkj.model.core.OpenIdModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/8/17.
 */
@Mapper
public interface BadgeMapper {

    List<Map> selectUserBadges(BadgeSearch model);

    Integer selectGetCount(String open_id);

    Integer selectNotGetCount(String open_id);

    List<Map> selectUserAchievement(UserAchievementSearch model);

    Map selectUserAchievementInfo(OpenIdModel model);

}
