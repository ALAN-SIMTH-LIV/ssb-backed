package com.ssb.entity.vo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 * @param <T> 数据
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PaginationVO<T> {
    private Integer pageNum; // 页码
    private Integer total; // 共计数据
    private Integer pageSize; // 每页大小
    private Integer pageTotal; // 共计页数
    private List<T> list = new ArrayList<T>(); // 返回数据

    public PaginationVO(Integer pageNum,Integer pageSize,Integer total,List<T> list){
        this.pageNum = pageNum;
        this.total = total;
        this.pageSize = pageSize;
        this.list = list;
    }

    public PaginationVO(Integer pageNum,Integer pageSize,Integer total,Integer pageTotal,List<T> list){
        if (pageNum == 0){
            pageNum = 1;
        }
        this.pageNum = pageNum;
        this.total = total;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.list = list;
    }
}
