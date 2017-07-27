package com.yrkj.mapper;

import com.yrkj.model.SysRole.SysRole;
import com.yrkj.model.SysRole.SysRoleDto;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/26.
 */
@Mapper
public interface SysRoleMapper {

    SysRole selectByCode(String code);

    SysRoleDto selectById(Long id);

    List<SysRoleDto> selectAll(SearchModel model);

    int insert(SysRole role);

    int update(SysRole role);

    int delete(IdsModel model);

}
