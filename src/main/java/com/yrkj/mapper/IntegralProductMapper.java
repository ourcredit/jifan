package com.yrkj.mapper;

import com.yrkj.model.Integral.IntegralProduct;
import com.yrkj.model.Integral.IntegralSearch;
import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 45425 on 2017/8/1.
 */
@Mapper
public interface IntegralProductMapper {
    int insert(IntegralProduct product);

    int update(IntegralProduct product);

    int delete(IdsModel model);

    List<IntegralProduct> selectAll(IntegralSearch model);
    IntegralProduct selectById(Long id);
    //批量更新状态
    int updateStatus(ChangeStatusModel model);
}
