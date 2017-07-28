package com.yrkj.mapper;

import com.yrkj.model.Shuffling.Shuffling;
import com.yrkj.model.Shuffling.ShufflingDto;
import com.yrkj.model.Shuffling.ShufflingSearch;
import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 45425 on 2017/7/25.
 */
@Mapper
public interface ShufflingMapper {
    int insert(Shuffling shuffling);
    int update(Shuffling shuffling);
    int delete(IdsModel model);
    List<ShufflingDto> selectAll(ShufflingSearch input);
    Shuffling selectById(Long id);
    //批量更新状态
    int updateStatus(ChangeStatusModel model);
}
