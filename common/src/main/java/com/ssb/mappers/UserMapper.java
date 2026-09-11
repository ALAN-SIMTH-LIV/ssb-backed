package com.ssb.mappers;

import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    Integer insert(User user);

    User select(User user);

    Integer count(@Param("query") UserQuery query);

    List<User> selectList(@Param("query") UserQuery query);
}
