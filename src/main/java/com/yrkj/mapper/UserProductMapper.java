package com.yrkj.mapper;

import com.yrkj.model.UserProduct.CartDeleteInput;
import com.yrkj.model.UserProduct.PerfectDto;
import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProductSearch;
import com.yrkj.model.core.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
    int deleteCart(CartDeleteInput model);

    //获取购物车列表
    List<Map> selectCart(String open_id);

    //获取单个商品价格
    Float selectPriceByProductId(Long product_id);

}
