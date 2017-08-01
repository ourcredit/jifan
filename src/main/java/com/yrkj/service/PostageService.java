package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.PostageMapper;
import com.yrkj.model.Shuffling.Shuffling;
import com.yrkj.model.Shuffling.ShufflingSearch;
import com.yrkj.model.core.*;
import com.yrkj.model.postages.Postage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 45425 on 2017/8/1.
 */
@Service
public class PostageService {
    private PostageMapper _postMapper;
    @Autowired
    public  PostageService( PostageMapper postMapper){
        _postMapper=postMapper;
    }
    /**
     * 创建分类
     * @param post
     * @return ActionResult
     */
    public ActionResult add(Postage post){

        if (_postMapper.insert(post) == 1){
            return new ActionResult(true,null,"创建成功");
        }
        return new ActionResult(false,null,"创建失败");
    }

    /**
     * 更新分类
     * @param post
     * @return ActionResult
     */
    public ActionResult update(Postage post){
        if (_postMapper.update(post) == 1){
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
        if (_postMapper.delete(models) == 1){
            return new ActionResult(true,null,"删除成功");
        }
        return new ActionResult(false,null,"删除失败");
    }

    /**
     * 获取详情
     * @param id
     * @return
     */
    public ActionResult GetDetail(Long id){
        Postage model=_postMapper.selectById(id);
        return new ActionResult(true,model,"获取成功");
    }

    /**
     * 获取所有分类
     * @return
     */
    public PageModel getAll(SearchModel input){
        Page page = PageHelper.startPage(input.getPageNum(),input.getPageSize());
        String name = input.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            input.setName("%" + name + "%");
        }else {

            input.setName(null);
        }
        List list = _postMapper.selectAll(input);
        if (list.size() > 0){
            return new PageModel(true,list,page.getTotal(),"获取成功");
        }else {
            return new PageModel(true,list,page.getTotal(),"暂无数据");
        }
    }
}
