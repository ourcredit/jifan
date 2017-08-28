package com.yrkj.mapper;

import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.product.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/6/18.
 */
@Mapper
public interface ProductMapper {

    int insert(Product product);

    int update(Product product);

    int delete(IdsModel model);

    List<ProductDto> selectAll(ProductSearch model);

    //获取单一商品
    List<ProductDto> selectAllSingle(ProductSearch model);

    //获取勋章商品
    List<ProductDto> selectBadge(ProductSearch model);

    ProductInfoDto selectById(Long id);

    //组合商品管理
    int deletePackage(Product product);

    int insertPackage(Product product);

    List<ProductDto> selectPackageById(Long id);

    //批量更新状态
    int updateStatus(ChangeStatusModel model);

    //批量插入商品码
    int insertProductCode(ProductCodeInput input);
}
