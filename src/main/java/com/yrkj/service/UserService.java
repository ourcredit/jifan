package com.yrkj.service;

import com.yrkj.mapper.UserMapper;
import com.yrkj.model.User.User;
import com.yrkj.model.core.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuenianxiang on 2017/7/10.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param openid
     * @return
     */
    public ActionResult login(String openid){

        User user = userMapper.selectByOpenId(openid);

        if (user == null){

            userMapper.insert(openid);

            return new ActionResult(true,null,"登录成功");

        }else {

            if (user.getMobile()!=null && user.getMobile().length() != 0){

                return new ActionResult(true,user,"登录成功");

            }else {
                return new ActionResult(true,null,"登录成功");
            }
        }
    }

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public ActionResult save(User user){
        if (userMapper.update(user) == 1){
            return new ActionResult(true,null,"保存成功");
        }else {
            return new ActionResult(true,null,"保存失败");
        }
    }

    /**
     * 根据openid获取信息
     * @param openid
     * @return
     */
    public ActionResult getById(String openid){
        return new ActionResult(true,userMapper.selectByOpenId(openid),"获取成功");
    }

}
