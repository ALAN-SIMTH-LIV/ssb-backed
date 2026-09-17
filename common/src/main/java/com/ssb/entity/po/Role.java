package com.ssb.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色实体类
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id; //角色 ID
    private String name; // 角色名称
    private String description; // 角色描述
    private LocalDateTime createAt;  // 创建时间
}
