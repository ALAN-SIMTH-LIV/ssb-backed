package com.ssb.service;

import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;

import java.util.Map;

public interface UserService {
    Boolean register(User user);

    User findById(User user);

    Map<String, Object> pageUser(UserQuery userQuery);
}
