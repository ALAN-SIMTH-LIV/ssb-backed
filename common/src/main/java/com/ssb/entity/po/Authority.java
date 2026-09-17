package com.ssb.entity.po;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限表
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id; // 权限ID
    private Integer parentId; // 父权限ID,常用菜单下的功能
    private String name; //权限名称
    private String description; // 权限描述
    private String resource; //
    private Integer type; // 0: 菜单 1: 接口
    private LocalDateTime createAt; // 接口的时候需要写 比如：blog:menu:user:info
}
