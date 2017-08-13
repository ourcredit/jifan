package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.ClienteleMapper;
import com.yrkj.model.clientele.ClienteleSearch;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.product.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/13.
 */
@Service
public class ClienteleService {

    @Autowired
    private ClienteleMapper clienteleMapper;

    /**
     * 获取客户列表
     * @param model
     * @return
     */
    public PageModel getAll(ClienteleSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String phone = model.getPhone();
        if (phone != null && phone.length() > 0){
            model.setPhone("%" + phone + "%");
        }else {
            model.setPhone(null);
        }

        String city = model.getCity();
        if (city != null && city.length() > 0){
            model.setCity("%" + city + "%");
        }else {
            model.setCity(null);
        }

        List list = clienteleMapper.selectAll(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }
}
