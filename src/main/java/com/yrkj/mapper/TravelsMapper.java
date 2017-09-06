package com.yrkj.mapper;

import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.order.OrderFilter;
import com.yrkj.model.travels.Travels;
import com.yrkj.model.travels.TravelsDto;
import com.yrkj.model.travels.TravelsInfoDto;
import com.yrkj.model.travels.TravelsSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/7/20.
 */
@Mapper
public interface TravelsMapper {

    int insert(Travels travels);

    int update(Travels travels);

    TravelsInfoDto selectById(Long id);

    List<TravelsDto> selectAll(TravelsSearch search);

    int delete(IdsModel model);
    List<Map> selectTravelsByFilter(OrderFilter input);
    //批量更新状态
    int updateStatus(ChangeStatusModel model);

}
