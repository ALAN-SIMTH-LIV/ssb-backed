package com.ssb.service;

import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.PaginationVO;
import com.ssb.entity.vo.UserVO;

public interface UserService {
    User loginOrRegister(User user);

    User findById(Integer id);

    PaginationVO<UserVO> pageUser(UserQuery userQuery);
}
