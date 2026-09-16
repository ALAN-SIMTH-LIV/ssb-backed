package com.ssb.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuery extends BaseParam{
    private Integer id; // 用户ID
    private String email; // 邮箱
    private String nickName; // 昵称
    private String emailFuzzy; // 邮箱搜索词
    private String nickNameFuzzy; // 昵称搜索词
}
