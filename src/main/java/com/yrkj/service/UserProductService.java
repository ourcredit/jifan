package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.UserProductMapper;
import com.yrkj.model.UserProduct.CartDeleteInput;
import com.yrkj.model.UserProduct.CartSearch;
import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProductSearch;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.core.SearchModel;
import com.yrkj.model.product.ProductSearch;
import com.yrkj.model.travels.Travels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

        Integer number = userProductMapper.selectCartNum(userCart);

        //判断购物车种是否已经存在该商品
        if (number == null){
            if (userProductMapper.insertCart(userCart) == 1){
                return new ActionResult(true,null,"添加成功");
            } else {
                return new ActionResult(false,null,"添加失败");
            }
        }else {
            number = number + userCart.getNumber();
            userCart.setNumber(number);
            if (userProductMapper.updateCartNum(userCart) == 1){
                return new ActionResult(true,null,"更新购物车数量成功");
            }else {
                return new ActionResult(false,null,"更新购物车数量失败");
            }
        }
    }

    /**
     * 删除购物车
     * @param input
     * @return
     */
    public ActionResult removeCart(CartDeleteInput input){

        if (userProductMapper.deleteCart(input) > 0){

            return new ActionResult(true,null,"删除成功");

        } else {

            return new ActionResult(false,null,"删除失败");

        }
    }

    /**
     * 获取购物车列表
     * @param open_id
     * @return
     */
    public ActionResult getCartList(String open_id){

        List list = userProductMapper.selectCart(open_id);

        if (list.size() > 0){
            return new ActionResult(true,list,"获取成功");
        }else {
            return new ActionResult(true,list,"暂无数据");
        }
    }


    /**
     * 获取商品详情页面
     * @param id
     * @return
     */
    public ActionResult getProductInfo(Long id){

        Map product = userProductMapper.selectProductById(id);

        if (product != null){
            List list = userProductMapper.selectSameProduct(id);
            product.put("sameList",list);
            return new ActionResult(true,product,"获取成功");
        }

        return new ActionResult(false,null,"获取失败");
    }

}
