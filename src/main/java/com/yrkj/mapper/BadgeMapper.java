package com.yrkj.mapper;

import com.yrkj.model.badge.BadgeSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/8/17.
 */
@Mapper
public interface BadgeMapper {

    List<Map> selectUserBadges(BadgeSearch model);
}
