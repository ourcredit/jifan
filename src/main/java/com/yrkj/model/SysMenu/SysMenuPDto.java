package com.yrkj.model.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/29.
 */
public class SysMenuPDto extends  SysMenuDto {

    public SysMenuPDto(){
        this.child = new ArrayList<>();
    }

    private List<SysMenuDto> child;

    public List<SysMenuDto> getChild() {
        return child;
    }

    public void setChild(List<SysMenuDto> child) {
        this.child = child;
    }
}
