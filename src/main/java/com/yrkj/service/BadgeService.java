package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.BadgeMapper;
import com.yrkj.model.badge.BadgeSearch;
import com.yrkj.model.core.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/17.
 */
@Service
public class BadgeService {

    @Autowired
    private BadgeMapper badgeMapper;

    /**
     * 获取用户勋章
     * @param model
     * @return
     */
    public PageModel getUserBadges(BadgeSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        List list = badgeMapper.selectUserBadges(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }
}
