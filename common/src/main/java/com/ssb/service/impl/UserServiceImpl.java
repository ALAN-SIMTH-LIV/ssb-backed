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
    @Transactional
    public User register(User user) {
        // 查找用户是否存在
        User existUser = userMapper.selectByEmail(user);
        if (null != existUser){
            // 用户存在
            throw new BusinessException("邮箱已存在");
        }

        // 生成默认昵称
        String nickName = RandomUtil.generateNickName(18);
        user.setNickName(nickName);
        // 默认密码统一先设置成123456
        user.setPassword(passwordEncoder.encode("123456"));
        // 用户不存在
        userMapper.insert(user);
        return userMapper.selectByEmail(user);
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
