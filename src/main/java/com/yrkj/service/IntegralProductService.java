package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.IntegralProductMapper;
import com.yrkj.model.Integral.IntegralProduct;
import com.yrkj.model.Integral.IntegralSearch;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.PageModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 45425 on 2017/8/1.
 */
@Service
public class IntegralProductService {
    @Autowired
    private IntegralProductMapper _productMapper;

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

}
