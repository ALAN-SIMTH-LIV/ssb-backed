package com.ssb.web.controller;

import com.ssb.entity.dto.UserQuery;
import com.ssb.entity.po.User;
import com.ssb.entity.vo.PaginationVO;
import com.ssb.entity.vo.ResponseVO;
import com.ssb.entity.vo.UserVO;
import com.ssb.exception.BusinessException;
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
            throw new BusinessException("账号已存在");
        }
        return ResponseSuccess();
    }

    @GetMapping("/{id}")
    public ResponseVO<UserVO> getUser(@PathVariable Integer id){
        User user = userService.findById(id);
        return ResponseSuccess(UserVO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .build());
    }

    @GetMapping
    public ResponseVO<PaginationVO<UserVO>> getUsers(
           UserQuery userQuery
    ){
        return ResponseSuccess(userService.pageUser(userQuery));
    }
}
