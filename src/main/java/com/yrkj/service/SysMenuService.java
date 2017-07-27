package com.yrkj.service;

import com.yrkj.mapper.SysMenuMapper;
import com.yrkj.model.SysMenu.SysMenuDto;
import com.yrkj.model.SysMenu.SysMenuPDto;
import com.yrkj.model.SysMenu.SysRoleMenuInput;
import com.yrkj.model.core.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/6/29.
 */
@Service
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取所有菜单
     * @return
     */
    public ActionResult getAllMenu(){
        List list = sysMenuMapper.selectAll();
        return new ActionResult(true,list,"获取成功");
    }

    /**
     * 根据role_id获取菜单
     * @param role_id
     * @return
     */
    public ActionResult getMenuByRoleId(Long role_id){
        List<SysMenuDto> has_list = sysMenuMapper.selectMenuByRoleId(role_id);
        List all_list = sysMenuMapper.selectAll();

        Map result = new HashMap();
        result.put("all_list",all_list);
        result.put("has_list",has_list);

        return new ActionResult(true,result,"获取成功");
    }

    /**
     * 根据user_id获取菜单
     * @return
     */
    public ActionResult getMenuByUserId(Long user_id){

        List<SysMenuDto> list = sysMenuMapper.selectMenuByUserId(user_id);

        List<SysMenuPDto> parentList = new ArrayList();

        for (SysMenuDto dto:list){
            if (dto.getPid() == 0){
                SysMenuPDto pDto = new SysMenuPDto();
                pDto.setId(dto.getId());
                pDto.setPid(dto.getPid());
                pDto.setTitle(dto.getTitle());
                pDto.setIcon(dto.getIcon());
                pDto.setPath(dto.getPath());
                parentList.add(pDto);
            }
        }

        for (SysMenuPDto pDto:parentList){
            for (SysMenuDto dto:list){
                if (dto.getId()!=0 && pDto.getId() == dto.getPid()){
                    pDto.getChild().add(dto);
                }
            }
        }

        return new ActionResult(true,parentList,"获取成功");
    }

    /**
     * 为角色分配菜单
     * @param input
     * @return
     */
    @Transactional
    public ActionResult addRoleMenu(SysRoleMenuInput input){

        //先删除关系
        sysMenuMapper.deleteRoleMenu(input);

        if (sysMenuMapper.insertRoleMenu(input) > 0){
            return new ActionResult(true,null,"分配成功");
        }
        return new ActionResult(false,null,"分配失败");
    }

}
