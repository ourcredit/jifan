package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.TravelsMapper;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.travels.Travels;
import com.yrkj.model.travels.TravelsDto;
import com.yrkj.model.travels.TravelsInfoDto;
import com.yrkj.model.travels.TravelsSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/21.
 */
@Service
public class TravelsService {

    @Autowired
    private TravelsMapper travelsMapper;

    /**
     * 创建游迹
     * @param travels
     * @return
     */
    public ActionResult add(Travels travels){

        if (travelsMapper.insert(travels) == 1){

            return new ActionResult(true,null,"创建成功");

        } else {

            return new ActionResult(false,null,"创建失败");

        }
    }

    /**
     * 更新游迹
     * @param travels
     * @return
     */
    public ActionResult update(Travels travels){

        if (travelsMapper.update(travels) == 1){

            return new ActionResult(true,null,"更新成功");

        }else {

            return new ActionResult(false,null,"更新失败");

        }
    }

    /**
     * 批量删除
     * @param model
     * @return
     */
    public ActionResult delete(IdsModel model){

        if (travelsMapper.delete(model) > 0){

            return new ActionResult(true,null,"删除成功");

        }

        return new ActionResult(false,null,"删除失败");
    }


    /**
     * 获取商品详情
     * @param id
     * @return
     */
    public ActionResult getById(Long id){

        TravelsInfoDto dto = travelsMapper.selectById(id);

        if (dto != null){

            return new ActionResult(true, dto,"获取成功");

        }else {
            return new ActionResult(false,null,"获取失败");

        }
    }

    /**
     * 获取游迹列表
     * @param model
     * @return
     */
    public PageModel getAll(TravelsSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = travelsMapper.selectAll(model);

        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
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

        if (travelsMapper.updateStatus(model) > 0){
            return new ActionResult(true,null,"更新成功");
        }else {
            return new ActionResult(false,null,"更新失败");
        }
    }
}
