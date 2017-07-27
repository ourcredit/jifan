package com.yrkj.mapper;

import com.yrkj.model.category.Category;
import com.yrkj.model.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/13.
 */
@Mapper
public interface CategoryMapper {

    int insert(Category category);

    int update(Category category);

    int delete(Category category);

    List<CategoryDto>selectAllByPid(Long pid);

    List<CategoryDto>selectAll();


}
