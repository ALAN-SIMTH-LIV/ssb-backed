package com.ssb.service.impl;

import com.ssb.entity.dto.SimplePage;
import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.mappers.UserMapper;
import com.ssb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> pageUser(UserQuery query) {
        if (null == query){
            query = new UserQuery();
        }
        Integer pageNum = query.getPageNum() == null ? 1 : query.getPageNum();
        Integer pageSize = query.getPageSize() == null ? 20 : query.getPageSize();
        Integer total = userMapper.selectCount(query);
        SimplePage simplePage = new SimplePage(pageNum,total,pageSize);
        query.setSimplePage(simplePage);
        List<User> users = userMapper.selectList(query);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        result.put("total", total);
        result.put("pageSize", pageSize);
        result.put("pageNum", pageNum);
        result.put("pageTotal", simplePage.getPageTotal());
        return result;
    }
}
