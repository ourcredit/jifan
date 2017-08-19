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
import com.yrkj.model.User.UserAddress;
import com.yrkj.model.core.*;

import com.yrkj.model.order.UserIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
        Integer ui = _userMapper.selectUserIntegrationVal(model.getOpen_id());

        if (model.getProduct_type() == 1 && model.getAddress().length() < 1){
            UserAddress ua = _userMapper.selectUserDefaultAddress(model.getOpen_id());
            if (ua!=null){
                model.setProvince_id(ua.getProvince_id());
                model.setProvince_name(ua.getProvince_name());
                model.setCity_id(ua.getCity_id());
                model.setCity_name(ua.getCity_name());
                model.setReceiver(ua.getReceiver());
                model.setAddress(ua.getAddress());
                model.setPhone(ua.getPhone());
            }else {
                return new ActionResult(false,null,"请设置收货地址");
            }
        }

        if(ui <= model.getOrder_cost()){
            return new ActionResult(false,null,"用户积分不足,无法购买");
        } else{
            model.setOrder_state(1);
        }

        if (_productMapper.InsertOrder(model) == 1){

            //插入积分明细表
            UserIntegration integration = new UserIntegration();
            integration.setOpen_id(model.getOpen_id());
            integration.setIntegration_val(-model.getOrder_cost());
            integration.setCreate_time(new Date());
            integration.setRemark("购买积分商品消费"+model.getOrder_cost()+"积分");
            _orderMapper.insertUserIntegration(integration);

            User u = new User();
            u.setOpen_id(model.getOpen_id());
            u.setIntegration_val(ui - model.getOrder_cost());
            _userMapper.UpdateUserIntegrationVal(u);

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

    /**
     * 抽奖
     * @return
     */
    @Transactional
    public  ActionResult lottery(String open_id){

        //各种校验
        Integer ui = _userMapper.selectUserIntegrationVal(open_id);

        if(ui < 5){
            return new ActionResult(false,null,"用户积分不足,无法购买");
        }

        UserAddress ua = _userMapper.selectUserDefaultAddress(open_id);
        if (ua!=null){

        } else {
            return new ActionResult(false,null,"请设置收货地址");
        }

        //扣除5积分

        //插入积分明细表
        UserIntegration integration = new UserIntegration();
        integration.setOpen_id(open_id);
        integration.setIntegration_val(-5);
        integration.setCreate_time(new Date());
        integration.setRemark("积分抽奖消耗5积分");
        _orderMapper.insertUserIntegration(integration);

        //更新用户表中的积分
        ui = ui - 5;
        User u = new User();
        u.setOpen_id(open_id);
        u.setIntegration_val(ui);
        _userMapper.UpdateUserIntegrationVal(u);

        int max= 10000;
        int min= 1;
        Random random = new Random();
        int r = random.nextInt(max)%(max-min+1) + min;

        if (r <= 3494){

           return new ActionResult(true,0,"谢谢惠顾");

        } else if (r > 3494 && r<=5494){

            addIntegrationVal(open_id,ui,5);

            return new ActionResult(true,1,"5积分");

        } else if (r > 5494 && r<=6494){

            addIntegrationVal(open_id,ui,10);

            return new ActionResult(true,2,"10积分");

        } else if (r > 6494 && r<=6504){

            addIntegrationVal(open_id,ui,50);

            return new ActionResult(true,3,"50积分");

        } else if (r > 6504 && r<=9998){

            return new ActionResult(true,0,"谢谢惠顾");

        } else {

            return new ActionResult(true,4,"随机勋章");
        }

    }

    public void addIntegrationVal(String open_id,Integer currentVal,Integer addVal){

        UserIntegration model = new UserIntegration();
        model.setOpen_id(open_id);
        model.setIntegration_val(addVal);
        model.setCreate_time(new Date());
        model.setRemark("抽奖获得"+addVal+"积分");
        _orderMapper.insertUserIntegration(model);

        //更新用户表中的积分
        User user = new User();
        user.setOpen_id(open_id);
        user.setIntegration_val(currentVal + addVal);
        _userMapper.UpdateUserIntegrationVal(user);
    }


}
