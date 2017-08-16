package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.IntegralProductMapper;
import com.yrkj.mapper.OrderMapper;
import com.yrkj.mapper.UserMapper;
import com.yrkj.model.Integral.CourierInput;
import com.yrkj.model.Integral.IntegralOrder;
import com.yrkj.model.Integral.IntegralProduct;
import com.yrkj.model.Integral.IntegralSearch;
import com.yrkj.model.User.User;
import com.yrkj.model.core.*;

import com.yrkj.model.order.UserIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 45425 on 2017/8/1.
 */
@Service
public class IntegralProductService {
    @Autowired
    private IntegralProductMapper _productMapper;
    @Autowired
    private UserMapper _userMapper;
    @Autowired
    private OrderMapper _orderMapper;
    /**
     * 创建商品
     * @param product
     * @return
     */
    public ActionResult add(IntegralProduct product){
        if (_productMapper.insert(product) == 1){
            return new ActionResult(true,null,"创建成功");
        }
        return new ActionResult(false,null,"创建失败");
    }

    /**
     * 更新商品
     * @param product
     * @return
     */
    public ActionResult update(IntegralProduct product){
        if (_productMapper.update(product) == 1){
            return new ActionResult(true,null,"更新成功");
        }
        return new ActionResult(false,null,"更新失败");

    }

    /**
     * 批量删除产品
     * @param model
     * @return
     */
    public ActionResult deleteProducts(IdsModel model){
        if (_productMapper.delete(model) > 0){
            return new ActionResult(true,null,"删除成功");
        }
        return new ActionResult(false,null,"删除失败");
    }
    /**
     * 批量更新上下线状态
     * @param model
     * @return
     */
    public ActionResult updateStatus(ChangeStatusModel model){

        if (model.getList() == null){
            return new ActionResult(false,null,"id不能为空");
        }

        if (model.getList().size() == 0){
            return new ActionResult(false,null,"id不能为空");
        }

        if (model.getStatus() < 0 || model.getStatus() > 1){
            return new ActionResult(false,null,"状态有误");
        }

        if (_productMapper.updateStatus(model) > 0){
            return new ActionResult(true,null,"更新成功");
        }else {
            return new ActionResult(false,null,"更新失败");
        }
    }

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    public ActionResult getById(Long id){

        IntegralProduct dto = _productMapper.selectById(id);

        if (dto != null){
            return new ActionResult(true,dto,"获取成功");
        }else {
            return new ActionResult(false,null,"获取失败");

        }
    }

    /**
     * 获取商品列表
     * @param model
     * @return
     */
    public PageModel getAll(IntegralSearch model){
        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = _productMapper.selectAll(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }
    /*
    * 获取所有商品 手机端*/
    public  PageModel GetIntegralList(SearchModel model){
        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = _productMapper.GetIntegralProducts(model);
        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }
    public  ActionResult GetIntegralDetail(Long id){
        IntegralProduct dto = _productMapper.GetIntegralById(id);
        if (dto != null){
            return new ActionResult(true,dto,"获取成功");
        }else {
            return new ActionResult(false,null,"获取失败");

        }
    }
    @Transactional
    public ActionResult InsertOrder(IntegralOrder model){
        Integer ui=_userMapper.selectUserIntegrationVal(model.getOpen_id());
        if(ui<=model.getOrder_cost()){

            return new ActionResult(false,null,"用户积分不足,无法购买");

        } else{
            model.setOrder_state(1);
        }
        if (_productMapper.InsertOrder(model) == 1){

            User u=new User();
            u.setOpen_id(model.getOpen_id());
            u.setIntegration_val(ui-model.getOrder_cost());
            _userMapper.UpdateUserIntegrationVal(u);

            //插入积分明细表
            UserIntegration integration = new UserIntegration();
            integration.setOpen_id(model.getOpen_id());
            integration.setIntegration_val(-model.getOrder_cost());
            integration.setCreate_time(new Date());
            integration.setRemark("购买积分商品消费"+model.getOrder_cost()+"积分");
            _orderMapper.insertUserIntegration(integration);

            return new ActionResult(true,null,"创建成功");
        }
        return new ActionResult(false,null,"创建失败");

    }

    public  PageModel OrderList(IntegralSearch model){
        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());
        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }
        List list = _productMapper.selectOrders(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    public  ActionResult UpdateCourier(CourierInput input){
        if (_productMapper.UpdateCourier(input) == 1){
            return new ActionResult(true,null,"更新成功");
        }
        return new ActionResult(false,null,"更新失败");
    }

}
