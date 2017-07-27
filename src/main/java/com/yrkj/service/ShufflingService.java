package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.ShufflingMapper;
import com.yrkj.model.Shuffling.Shuffling;
import com.yrkj.model.Shuffling.ShufflingSearch;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 45425 on 2017/7/25.
 */
@Service
public class ShufflingService {
    @Autowired
    private ShufflingMapper _shufflingMapper;
    /**
     * 创建分类
     * @param shuffling
     * @return
     */
    public ActionResult add(Shuffling shuffling){

        if (_shufflingMapper.insert(shuffling) == 1){
            return new ActionResult(true,null,"创建成功");
        }

        return new ActionResult(false,null,"创建失败");
    }

    /**
     * 更新分类
     * @param shuffling
     * @return
     */
    public ActionResult update(Shuffling shuffling){
        if (_shufflingMapper.update(shuffling) == 1){
            return new ActionResult(true,null,"更新成功");
        }
        return new ActionResult(false,null,"更新失败");
    }

    /**
     * 删除分类
     * @param models
     * @return
     */
    public ActionResult delete(IdsModel models){
        if (_shufflingMapper.delete(models) == 1){
            return new ActionResult(true,null,"删除成功");
        }
        return new ActionResult(false,null,"删除失败");
    }

    /**
     * 根据父级获取分类
     * @param id
     * @return
     */
    public ActionResult GetDetail(Long id){
        return new ActionResult(true,_shufflingMapper.selectById(id),"获取成功");
    }

    /**
     * 获取所有分类
     * @return
     */
    public PageModel getAll(ShufflingSearch input){
        Page page = PageHelper.startPage(input.getPageNum(),input.getPageSize());
        String name = input.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            input.setName("%" + name + "%");
        }else {
            input.setName(null);
        }
        List list = _shufflingMapper.selectAll(input);
        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }

}
