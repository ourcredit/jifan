package com.yrkj.mapper;

import com.yrkj.model.UserProduct.PerfectDto;
import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProductSearch;
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

    //商品列表
    List<PerfectDto> selectProductList(UserProductSearch model);


    //新增购物车
    int insertCart(UserCart model);

    //删除购物车商品
    int deleteCart(UserCart model);

}
