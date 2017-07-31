package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.UserProductMapper;
import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProductSearch;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.core.SearchModel;
import com.yrkj.model.travels.Travels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/29.
 */
@Service
public class UserProductService {

    @Autowired
    private UserProductMapper userProductMapper;

    /**
     * 获取精选列表
     * @param model
     * @return
     */
    public PageModel getPerfectList(SearchModel model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = userProductMapper.selectPerfectList(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    /**
     * 获取商品列表
     * @param model
     * @return
     */
    public PageModel getProductList(UserProductSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = userProductMapper.selectProductList(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }


    /**
     * 添加商品到购物车
     * @param userCart
     * @return
     */
    public ActionResult addCart(UserCart userCart){

        if (userProductMapper.insertCart(userCart) == 1){

            return new ActionResult(true,null,"创建成功");

        } else {

            return new ActionResult(false,null,"创建失败");

        }
    }

    /**
     * 删除购物车
     * @param userCart
     * @return
     */
    public ActionResult removeCart(UserCart userCart){

        if (userProductMapper.deleteCart(userCart) == 1){

            return new ActionResult(true,null,"删除成功");

        } else {

            return new ActionResult(false,null,"删除失败");

        }
    }


    /**
     *
     * @param product_id
     * @param open_id
     * @return
     */
    public ActionResult buyProduct(String product_id, String open_id){

        //备用


        return null;
    }

}
