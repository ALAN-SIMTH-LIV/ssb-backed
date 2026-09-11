package com.ssb.web.controller;

import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.UserVO;
import com.ssb.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/test")
    private String test(){
        return "这里是WEB端";
    }

    @PostMapping
    public String addUser(
            @RequestBody User user
            ){
        if (!userService.register(user)){
            return "账号: "+ user.getUserName()+" 已存在！";
        }
        userService.register(user);
        return "注册成功！";
    }

    @GetMapping("/{id}")
    public UserVO getUser(@PathVariable Integer id){
        User user = userService.findById(User.builder().id(id).build());
        return UserVO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .build();
    }

    @GetMapping
    public Map<String, Object> getUsers(
           UserQuery userQuery
    ){
        return userService.pageUser(userQuery);
    }
}
