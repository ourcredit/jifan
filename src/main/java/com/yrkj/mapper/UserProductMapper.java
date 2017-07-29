package com.yrkj.mapper;

import com.yrkj.model.UserProduct.PerfectDto;
import com.yrkj.model.core.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/29.
 */
@Mapper
public interface UserProductMapper {

    //精选列表
    List<PerfectDto> selectPerfectList(SearchModel model);

}
