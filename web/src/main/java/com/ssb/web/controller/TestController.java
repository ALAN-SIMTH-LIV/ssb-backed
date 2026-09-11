package com.ssb.web.controller;

import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.ResponseVO;
import com.ssb.entity.vo.UserVO;
import com.ssb.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
public class TestController extends ABaseController{

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseVO<Void> addUser(
            @RequestBody User user
            ){
        if (!userService.register(user)){
            // TODO 这里应该抛出异常
            return ResponseSuccess();
        }
        userService.register(user);
        return ResponseSuccess();
    }

    @GetMapping("/{id}")
    public ResponseVO<UserVO> getUser(@PathVariable Integer id){
        User user = userService.findById(User.builder().id(id).build());
        return ResponseSuccess(UserVO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .build());
    }

    @GetMapping
    // TODO 这里少个分页类
    public ResponseVO<Map<String, Object>> getUsers(
           UserQuery userQuery
    ){
        return ResponseSuccess(userService.pageUser(userQuery));
    }
}
