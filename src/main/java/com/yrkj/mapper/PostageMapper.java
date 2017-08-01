package com.yrkj.mapper;

import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.SearchModel;
import com.yrkj.model.postages.Postage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 45425 on 2017/8/1.
 */
@Mapper
public interface PostageMapper {
    int insert(Postage post);

    int update(Postage post);

    int delete(IdsModel model);
    Postage selectById(Long id);
    List<Postage> selectAll(SearchModel model);
}
