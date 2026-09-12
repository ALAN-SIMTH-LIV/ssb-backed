package com.ssb.service.impl;

import com.ssb.entity.dto.SimplePage;
import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.PaginationVO;
import com.ssb.entity.vo.UserVO;
import com.ssb.mappers.UserMapper;
import com.ssb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper<User,UserQuery> userMapper;

    @Override
    public Boolean register(User user) {
        User user1 = userMapper.select(user);
        if (null == user1){
            userMapper.insert(user);
            return true;
        }
        if (user1.getUserName().equals(user.getUserName())){
            return false;
        }
        return true;
    }

    @Override
    public User findById(User user) {
        return userMapper.select(user);
    }

    @Override
    public PaginationVO<UserVO> pageUser(UserQuery query) {
        if (null == query){
            query = new UserQuery();
        }
        Integer pageNum = query.getPageNum() == null ? 1 : query.getPageNum();
        Integer pageSize = query.getPageSize() == null ? 20 : query.getPageSize();
        Integer total = userMapper.selectCount(query);
        SimplePage simplePage = new SimplePage(pageNum,total,pageSize);
        query.setSimplePage(simplePage);

        List<User> users = userMapper.selectList(query);

        List<UserVO> userVOS = users.stream().map(
                user -> {
                    return UserVO.builder()
                            .id(user.getId())
                            .userName(user.getUserName())
                            .build();
                }
        ).collect(Collectors.toList());
        return PaginationVO.<UserVO>builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .pageTotal(simplePage.getPageTotal())
                .total(total)
                .list(userVOS)
                .build();
    }
}
