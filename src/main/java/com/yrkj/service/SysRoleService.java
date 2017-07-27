package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.SysRoleMapper;
import com.yrkj.model.SysRole.SysRole;
import com.yrkj.model.SysRole.SysRoleDto;
import com.yrkj.model.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/26.
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 创建角色
     * @param role
     * @return
     */
    public ActionResult addRole(SysRole role){

        //验证角色是否存在
        if (sysRoleMapper.selectByCode(role.getCode()) != null){
            return new ActionResult(false,null,"角色已存在");
        }

        if (sysRoleMapper.insert(role) == 1){
            return new ActionResult(true,null,"创建成功");
        }

        return new ActionResult(false,null,"创建失败");
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    public ActionResult updateRole(SysRole role){
        if (sysRoleMapper.update(role) > 0){
            return new ActionResult(true,null,"更新成功");
        } else {
            return new ActionResult(false,null,"更新失败");
        }
    }

    /**
     * 根据id获取角色信息
     * @param model
     * @return
     */
    public ActionResult getById(IdModel model){

        SysRoleDto roleDto = sysRoleMapper.selectById(model.getId());

        if (roleDto != null){
            return new ActionResult(true,roleDto,"获取成功");
        }

        return new ActionResult(false,null,"获取失败");
    }

    /**
     * 获取角色列表
     * @param model
     * @return
     */
    public PageModel getAll(SearchModel model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = sysRoleMapper.selectAll(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    /**
     * 批量删除角色
     * @param model
     * @return
     */
    public ActionResult deleteRoles(IdsModel model){
        if (sysRoleMapper.delete(model) > 0){
            return new ActionResult(true,null,"删除成功");
        }
        return new ActionResult(false,null,"删除失败");
    }

}
