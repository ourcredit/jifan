package com.yrkj.mapper;

import com.yrkj.model.SysMenu.SysMenuDto;
import com.yrkj.model.SysMenu.SysRoleMenuInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/28.
 */
@Mapper
public interface SysMenuMapper {

    int deleteRoleMenu(SysRoleMenuInput input);

    int insertRoleMenu(SysRoleMenuInput input);

    List<SysMenuDto> selectAll();

    List<SysMenuDto> selectMenuByUserId(Long user_id);

    List<SysMenuDto> selectMenuByRoleId(Long role_id);
}
