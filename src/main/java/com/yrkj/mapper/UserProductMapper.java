package com.yrkj.mapper;

import com.yrkj.model.UserProduct.*;
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

    //查询该购物车种是否存在该商品 并返回数量
    Integer selectCartNum(UserCart model);

    //更新购物车商品数量
    int updateCartNum(UserCart model);

    //获取购物车列表
    List<Map> selectCart(String open_id);

    //获取单个商品名称+价格
    PayProductInput selectProductInfo(Long product_id);

    //获取商品详情
    Map selectProductById(Long id);

    List<Map> selectSameProduct(Long id);

}
