package com.yrkj.mapper;

import com.yrkj.model.SysUser.SysUser;
import com.yrkj.model.SysUser.SysUserDto;
import com.yrkj.model.SysUser.SysUserInput;
import com.yrkj.model.SysUser.SysUserRoleInput;
import com.yrkj.model.core.IdModel;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/6/16.
 */
@Mapper
public interface SysUserMapper {

    SysUser select(SysUserInput input);

    SysUserDto selectById(IdModel model);

    List<SysUserDto> selectAll(SearchModel model);

    int insert(SysUser user);

    int update(SysUser user);

    int delete(IdsModel model);

    //关系
    int insertUserRole(SysUserRoleInput input);

    int deleteUserRole(SysUserRoleInput input);
}
