package com.ssb.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimplePage {
    private Integer pageNum; // 页码
    private Integer total; // 共计数据
    private Integer pageSize; // 每页大小
    private Integer pageTotal; // 共计页数
    private Integer start; // 偏移值 = ( 页码 - 1 ) * 每页大小
    private Integer end; // 每页大小

    public SimplePage(Integer pageNum, Integer total, Integer pageSize) {
        if (null == pageNum) {
            pageNum = 0;
        }
        this.pageNum = pageNum;
        this.total = total;
        this.pageSize = pageSize;
        action();
    }

    public SimplePage(Integer start,Integer end){
        this.start = start;
        this.end = end;
    }

    public void action(){
        if (this.pageSize <= 0) {
            this.pageSize = 20;
        }
        if (this.total > 0) {
            this.pageTotal = this.total % this.pageSize == 0 ? this.total / this.pageSize
                    : this.total / this.pageSize + 1;
        } else {
            pageTotal = 1;
        }

        if (pageNum <= 1) {
            pageNum = 1;
        }
        if (pageNum > pageTotal) {
            pageNum = pageTotal;
        }
        this.start = (pageNum - 1) * pageSize;
        this.end = this.pageSize;
    }

    public void setTotal(Integer total) {
        this.total = total;
        this.action();
    }
}
