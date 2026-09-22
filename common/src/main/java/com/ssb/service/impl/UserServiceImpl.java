package com.ssb.service.impl;

import com.ssb.entity.dto.SimplePage;
import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.PaginationVO;
import com.ssb.entity.vo.UserVO;
import com.ssb.exception.BusinessException;
import com.ssb.mappers.UserMapper;
import com.ssb.service.UserService;
import com.ssb.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper<User,UserQuery> userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User loginOrRegister(User user) {
        // 查看用户是否存在
        User existUser = userMapper.selectByEmail(user);
        // 用户存在
        if (existUser != null){
            return existUser;
        }
        // 用户不存在,注册新用户
        User newUser = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode("123456"))
                .nickName(RandomUtil.generateNickName(18))
                .build();
        // 唯一键冲突
        try{
            userMapper.insert(newUser);
        } catch (DuplicateKeyException e){
            return userMapper.selectByEmail(user);
        }
        return newUser;
    }


    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
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
                            .email(user.getEmail())
                            .nickName(user.getNickName())
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
