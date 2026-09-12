package com.ssb.service;

import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.PaginationVO;
import com.ssb.entity.vo.UserVO;

import java.util.Map;

public interface UserService {
    Boolean register(User user);

    User findById(User user);

    PaginationVO<UserVO> pageUser(UserQuery userQuery);
}
