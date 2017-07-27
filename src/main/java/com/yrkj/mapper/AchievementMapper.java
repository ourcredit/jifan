package com.yrkj.mapper;

import com.yrkj.model.achievement.Achievement;
import com.yrkj.model.achievement.AchievementDto;
import com.yrkj.model.achievement.AchievementInfoDto;
import com.yrkj.model.achievement.AchievementSearch;
import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/7/21.
 */
@Mapper
public interface AchievementMapper {

    int insert(Achievement achievement);

    int update(Achievement achievement);

    AchievementInfoDto selectById(Long id);

    List<AchievementDto> selectAll(AchievementSearch search);

    int delete(IdsModel model);

    int deleteRelation(Achievement achievement);

    int insertRelation(Achievement achievement);

    List<Map> selectProductById(Long id);

    List<Map> selectTravelsById(Long id);

    //批量更新状态
    int updateStatus(ChangeStatusModel model);
}
