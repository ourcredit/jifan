package com.yrkj.service;

import com.yrkj.mapper.UserMapper;
import com.yrkj.model.User.User;
import com.yrkj.model.User.UserAddress;
import com.yrkj.model.User.UserDesignation;
import com.yrkj.model.core.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 获取用户成就 积分 勋章数
     * @param open_id
     * @return
     */
    public ActionResult getUserVal(String open_id){

        Integer achievement_val = userMapper.selectUserAchievementVal(open_id);

        Integer integration_val = userMapper.selectUserIntegrationVal(open_id);

        Integer badge = userMapper.selectUserBadge(open_id);

        Map result = new HashMap();
        result.put("achievement_val",achievement_val);
        result.put("integration_val",integration_val);
        result.put("badge",badge);

        return new ActionResult(true,result,"获取成功");

    }

    /**
     * 根据openid获取信息
     * @param openid
     * @return
     */
    public ActionResult getById(String openid){
        return new ActionResult(true,userMapper.selectByOpenId(openid),"获取成功");
    }

    /**
     * 获取用户称号列表
     * @param open_id
     * @return
     */
    public ActionResult getDesignations(String open_id){
       List<Map> result = userMapper.selectDesignationList(open_id);
       return new ActionResult(true,result,"获取成功");
    }

    /**
     * 新增收货地址
     * @param address
     * @return
     */
    public ActionResult addAddress(UserAddress address){

        List list = userMapper.selectUserAddressList(address.getOpen_id());

        if (list.size() == 0){
            //如果不存在收货地址 则此地址设置为默认
            address.setIs_default(1);
        }

        if (userMapper.insertUserAddress(address) == 1){
            return new ActionResult(true,null,"添加成功");
        }else {
            return new ActionResult(false,null,"添加失败");
        }

    }

    /**
     * 更新收货地址
     * @param address
     * @return
     */
    public ActionResult updateAddress(UserAddress address){
        if (userMapper.updateUserAddress(address) == 1){
            return new ActionResult(true,null,"修改成功");
        } else {
            return new ActionResult(false,null,"修改失败");
        }
    }

    /**
     * 修改用户称号
     * @param designation
     * @return
     */
    public ActionResult updateDesignation(UserDesignation designation){

        if (userMapper.UpdateUserDesignation(designation) == 1){
            return new ActionResult(true,null,"修改成功");
        } else {
            return new ActionResult(false,null,"修改失败");
        }
    }

    /**
     * 获取收货地址列表
     * @param open_id
     * @return
     */
    public ActionResult getAddressList(String open_id){
        return new ActionResult(true,userMapper.selectUserAddressList(open_id),"获取成功");
    }

    /**
     * 获取默认收货地址
     * @param open_id
     * @return
     */
    public ActionResult getDefaultAddress(String open_id){
        return new ActionResult(true,userMapper.selectDefaultAddressPrice(open_id),"获取成功");
    }

    /**
     * 获取收货地址详情
     * @param id
     * @return
     */
    public ActionResult getAddressInfo(Long id){
        return new ActionResult(true,userMapper.selectUserAddressInfo(id),"获取成功");
    }

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    public ActionResult deleteAddress(Long id){
        if (userMapper.deleteUserAddress(id) == 1){
            return new ActionResult(true,null,"删除成功");
        } else {
            return new ActionResult(false,null,"删除失败");
        }
    }

    /**/
    public ActionResult getShuffling(){

        List rotationList = userMapper.selectShuffling(1);

        List bannerList = userMapper.selectShuffling(2);

        Map result = new HashMap();
        result.put("rotationList",rotationList);
        result.put("bannerList",bannerList);

        return new ActionResult(true,result,"获取成功");

    }

}
