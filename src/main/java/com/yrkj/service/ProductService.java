package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.ProductMapper;
import com.yrkj.model.core.*;
import com.yrkj.model.product.Product;
import com.yrkj.model.product.ProductDto;
import com.yrkj.model.product.ProductInfoDto;
import com.yrkj.model.product.ProductSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/21.
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 创建商品
     * @param product
     * @return
     */
    @Transactional
    public ActionResult add(Product product){

        if (product.getType() == 1){

            if (productMapper.insert(product) == 1){
                return new ActionResult(true,null,"创建成功");
            }

            return new ActionResult(false,null,"创建失败");
        }else if (product.getType() == 2){

            if (product.getIs_badge() == 1){
                return new ActionResult(false,null,"组合商品不能是勋章");
            }

            productMapper.insert(product);

            productMapper.insertPackage(product);

            return new ActionResult(true,null,"创建成功");

        }else {
            return new ActionResult(false,null,"创建失败");

        }
    }

    /**
     * 更新商品
     * @param product
     * @return
     */
    @Transactional
    public ActionResult update(Product product){
        if (product.getType() == 1){

            if (productMapper.update(product) == 1){
                return new ActionResult(true,null,"更新成功");
            }

            return new ActionResult(false,null,"更新失败");

        }else if (product.getType() == 2){

            if (product.getIs_badge() == 1){
                return new ActionResult(false,null,"组合商品不能是勋章");
            }

            productMapper.update(product);

            productMapper.deletePackage(product);

            productMapper.insertPackage(product);

            return new ActionResult(true,null,"更新成功");

        }else {
            return new ActionResult(false,null,"更新失败");
        }
    }

    /**
     * 批量删除产品
     * @param model
     * @return
     */
    public ActionResult deleteProducts(IdsModel model){
        if (productMapper.delete(model) > 0){
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

        if (productMapper.updateStatus(model) > 0){
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

        ProductInfoDto dto = productMapper.selectById(id);

        if (dto != null){
            dto.setList(productMapper.selectPackageById(id));
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
    public PageModel getAll(ProductSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = productMapper.selectAll(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    /**
     * 获取商品列表(单一商品)
     * @param model
     * @return
     */
    public PageModel getAllSingle(ProductSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = productMapper.selectAllSingle(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

    /**
     * 获取勋章商品列表
     * @param model
     * @return
     */
    public PageModel getBadgeList(ProductSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = productMapper.selectBadge(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

}
