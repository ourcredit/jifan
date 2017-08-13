package com.yrkj.mapper;

import com.yrkj.model.clientele.ClienteleDto;
import com.yrkj.model.clientele.ClienteleSearch;
import com.yrkj.model.core.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/13.
 */
@Mapper
public interface ClienteleMapper {

    List<ClienteleDto> selectAll(ClienteleSearch searchModel);
}
