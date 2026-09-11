package com.ssb.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseParam {
    private SimplePage simplePage; // 分页对象
    private Integer pageNum; // 页码
    private Integer pageSize; // 每页大小
    private String orderBy;  // 排序方式
}
