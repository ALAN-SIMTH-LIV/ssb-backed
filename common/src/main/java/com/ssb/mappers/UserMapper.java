package com.ssb.mappers;

import com.ssb.entity.po.User;

public interface UserMapper<T,P> extends BaseMapper<T,P>{
    Integer insert(User user);

    User selectByUserName(User user);

    User selectById(Integer id);
}
