package com.yrkj.mapper;

import com.yrkj.model.SysCommon.MessageCode;
import com.yrkj.model.User.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/7/22.
 */
@Mapper
public interface SysCommonMapper {

    /**
     * 获取省、市列表
     * @param id
     * @return
     */
    List<Map> getPositionsById(Long id);

    /**
     * 插入短信验证码
     * @param messageCode
     * @return
     */
    int insertMessageCode(MessageCode messageCode);

    /**
     * 查询当天已发送短信验证码
     * @param messageCode
     * @return
     */
    List<MessageCode> selectMessageCode(MessageCode messageCode);

    /**
     * 查询最新一条短信验证码
     * @param messageCode
     * @return
     */
    MessageCode selectLastMessageCode(MessageCode messageCode);

    /**
     * 绑定手机号
     * @param user
     * @return
     */
    int bindModile(User user);

}
