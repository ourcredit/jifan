package com.yrkj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yrkj.mapper.AchievementMapper;
import com.yrkj.model.achievement.Achievement;
import com.yrkj.model.achievement.AchievementDto;
import com.yrkj.model.achievement.AchievementInfoDto;
import com.yrkj.model.achievement.AchievementSearch;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.ChangeStatusModel;
import com.yrkj.model.core.IdsModel;
import com.yrkj.model.core.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/21.
 */
@Service
public class AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;

    /**
     * 创建成就
     * @param achievement
     * @return
     */
    @Transactional
    public ActionResult add(Achievement achievement){

        if (achievement.getType() == 1 || achievement.getType() == 2){

            achievementMapper.insert(achievement);

            achievementMapper.insertRelation(achievement);

            return new ActionResult(true,null,"创建成功");

        }else {

            return new ActionResult(false,null,"创建失败");

        }
    }

    /**
     * 更新成就
     * @param achievement
     * @return
     */
    @Transactional
    public ActionResult update(Achievement achievement){
        if (achievement.getType() == 1 || achievement.getType() == 2){

            achievementMapper.update(achievement);

            achievementMapper.deleteRelation(achievement);

            achievementMapper.insertRelation(achievement);

            return new ActionResult(true,null,"更新成功");

        }else {

            return new ActionResult(false,null,"更新失败");

        }
    }

    /**
     * 批量删除成就
     * @param model
     * @return
     */
    public ActionResult delete(IdsModel model){

        if (achievementMapper.delete(model) > 0){

            return new ActionResult(true,null,"删除成功");

        }

        return new ActionResult(false,null,"删除失败");
    }

    /**
     * 获取成就详情
     * @param id
     * @return
     */
    public ActionResult getById(Long id){

        AchievementInfoDto dto = achievementMapper.selectById(id);

        if (dto != null){
            if (dto.getType() == 1){
                dto.setList(achievementMapper.selectProductById(id));
            } else if (dto.getType() == 2){
                dto.setList(achievementMapper.selectTravelsById(id));
            } else {
                return new ActionResult(false,null,"获取失败");
            }
            return new ActionResult(true,dto,"获取成功");
        }else {
            return new ActionResult(false,null,"获取失败");
        }
    }

    /**
     * 获取成就列表
     * @param model
     * @return
     */
    public PageModel getAll(AchievementSearch model){

        Page page = PageHelper.startPage(model.getPageNum(),model.getPageSize());

        String name = model.getName();
        //name模糊查询
        if (name != null && name.length() > 0){
            model.setName("%" + name + "%");
        }else {
            model.setName(null);
        }

        List list = achievementMapper.selectAll(model);

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

        if (model.getStatus() != 1){
            return new ActionResult(false,null,"状态有误");
        }

        if (achievementMapper.updateStatus(model) > 0){
            return new ActionResult(true,null,"更新成功");
        }else {
            return new ActionResult(false,null,"更新失败");
        }
    }

}
