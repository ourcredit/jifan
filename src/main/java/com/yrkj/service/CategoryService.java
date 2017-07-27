package com.yrkj.service;

import com.yrkj.mapper.CategoryMapper;
import com.yrkj.model.category.Category;
import com.yrkj.model.core.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuenianxiang on 2017/7/13.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 创建分类
     * @param category
     * @return
     */
    public ActionResult add(Category category){

        if (categoryMapper.insert(category) == 1){
            return new ActionResult(true,null,"创建成功");
        }

        return new ActionResult(false,null,"创建失败");
    }

    /**
     * 更新分类
     * @param category
     * @return
     */
    public ActionResult update(Category category){
        if (categoryMapper.update(category) == 1){
            return new ActionResult(true,null,"更新成功");
        }
        return new ActionResult(false,null,"更新失败");
    }

    /**
     * 删除分类
     * @param category
     * @return
     */
    public ActionResult delete(Category category){
        if (categoryMapper.delete(category) == 1){
            return new ActionResult(true,null,"删除成功");
        }
        return new ActionResult(false,null,"删除失败");
    }

    /**
     * 根据父级获取分类
     * @param pid
     * @return
     */
    public ActionResult getAllByPid(Long pid){
         return new ActionResult(true,categoryMapper.selectAllByPid(pid),"获取成功");
    }

    /**
     * 获取所有分类
     * @return
     */
    public ActionResult getAll(){
        return new ActionResult(true,categoryMapper.selectAll(),"获取成功");
    }

}
