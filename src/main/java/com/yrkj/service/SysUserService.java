package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.SysUserMapper;
import com.yrkj.model.SysUser.SysUser;
import com.yrkj.model.SysUser.SysUserDto;
import com.yrkj.model.SysUser.SysUserInput;
import com.yrkj.model.SysUser.SysUserRoleInput;
import com.yrkj.model.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/16.
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 创建用户
     * @param user
     * @return
     */
    public ActionResult addUser(SysUser user){

        //验证用户是否存在
        SysUserInput input = new SysUserInput();
        input.setUser_name(user.getUser_name());
        SysUser checkUser = sysUserMapper.select(input);

        if (checkUser != null){
            return new ActionResult(false,null,"用户已存在");
        }

        if (sysUserMapper.insert(user) == 1){
            return new ActionResult(true,null,"创建成功");
        }
        return new ActionResult(false,null,"创建失败");
    }

    /**
     * 获取用户列表
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

        List list = sysUserMapper.selectAll(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    /**
     * 根据id获取用户信息
     * @param model
     * @return
     */
    public ActionResult getById(IdModel model){

        SysUserDto user = sysUserMapper.selectById(model);

        if (user != null){
            return new ActionResult(true,user,"获取成功");
        }

        return new ActionResult(false,null,"获取失败");
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    public ActionResult updateUser(SysUser user){
        if (sysUserMapper.update(user) == 1){
            return new ActionResult(true,null,"更新成功");
        }
        return new ActionResult(false,null,"更新失败");
    }

    /**
     * 批量删除用户
     * @param model
     * @return
     */
    public ActionResult deleteUsers(IdsModel model){
        if (sysUserMapper.delete(model) > 0){
            return new ActionResult(true,null,"删除成功");
        }
        return new ActionResult(false,null,"删除失败");
    }

    /**
     * 为人员分配角色
     * @param input
     * @return
     */
    @Transactional
    public ActionResult addUserRole(SysUserRoleInput input){

        //先删除关系
        sysUserMapper.deleteUserRole(input);

        if (sysUserMapper.insertUserRole(input) > 0){
            return new ActionResult(true,null,"分配成功");
        }
        return new ActionResult(false,null,"分配失败");
    }

}
