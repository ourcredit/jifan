package com.yrkj.mapper;

import com.yrkj.model.User.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by xuenianxiang on 2017/7/10.
 */
@Mapper
public interface UserMapper {

    int insert(String open_id);

    User selectByOpenId(String open_id);

    int update(User user);
}
