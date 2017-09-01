package com.yrkj.service;

import com.yrkj.mapper.OrderMapper;
import com.yrkj.mapper.UserMapper;
import com.yrkj.mapper.UserTravelsMapper;
import com.yrkj.model.User.User;
import com.yrkj.model.User.UserTravels;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.order.UserAchievement;
import com.yrkj.model.order.UserIntegration;
import com.yrkj.model.travels.UserTravelInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/19.
 */
@Service
public class UserTravelsService {

    @Autowired
    private UserTravelsMapper userTravelsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    //获取所有游迹
    public ActionResult getTravels(){
        return new ActionResult(true,userTravelsMapper.selectTravels(),"获取成功");
    }

    //获取用户已获得游迹
    public ActionResult getUserTravels(String open_id){
        return new ActionResult(true,userTravelsMapper.selectUserHasTravels(open_id),"获取成功");
    }
    public ActionResult IsSingIn(String open_id){
        Date left=getStartTime();
        Date right=getEndTime();
        UserTravelInput input =new UserTravelInput();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        input.setLeft(sdf.format(left));input.setRight(sdf.format(right));input.setOpen_id(open_id);
        List<UserTravels> result=userTravelsMapper.selectTodayTravel(input);
        Boolean res=result.size()>0;
        return new ActionResult(res,res,"");
    }
    private static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    private static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }
    //签到
    @Transactional
    public ActionResult insertUserTravels(UserTravels travels){

        Date now = new Date();

        travels.setCreate_time(now);


        UserTravels temp = userTravelsMapper.selectUserTravels(travels);

        if (temp != null){
            return new ActionResult(false,null,"只能签到一次");
        } else {
            //插入记录
            userTravelsMapper.insertUserTravels(travels);
        }

        List<UserAchievement> achievementList = userTravelsMapper.selectCurrentTravelsAchievement(travels.getOpen_id());
        //插入成就
        for (UserAchievement achievement:achievementList){
            achievement.setOpen_id(travels.getOpen_id());
            achievement.setCreate_time(now);
            orderMapper.insertUserAchievement(achievement);
        }

        //插入积分
        for (UserAchievement achievement:achievementList){
            UserIntegration integration = new UserIntegration();
            integration.setOpen_id(travels.getOpen_id());
            integration.setCreate_time(now);
            integration.setIntegration_val(achievement.getIntegration());
            integration.setRemark("获得"+achievement.getAchievement_name());
            orderMapper.insertUserIntegration(integration);
        }

        Integer achievement_val = userMapper.selectUserAchievementVal(travels.getOpen_id());

        Integer integration_val = userMapper.selectUserIntegrationVal(travels.getOpen_id());

        Integer badge = userMapper.selectUserBadge(travels.getOpen_id());

        User user = new User();
        user.setAchievement_val(achievement_val);
        user.setIntegration_val(integration_val);
        user.setBadge_count(badge);
        //更新成就 积分 勋章数
        userMapper.updateUserVal(user);

        return new ActionResult(true,null,"签到成功");

    }
}
