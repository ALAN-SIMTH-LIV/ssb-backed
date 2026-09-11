package com.ssb.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuery extends BaseParam{
    private Integer id; // 用户ID
    private String userName; // 账号
    private String userNameFuzzy; // 搜索词
}
