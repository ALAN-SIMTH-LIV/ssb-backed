package com.ssb.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T,P> {
    // P 查询参数类型
    /**
     * selectList:(根据参数查询集合)
     */
    List<T> selectList(@Param("query") P p);

    /**
     * selectCount:(根据集合查询数量)
     */
    Integer selectCount(@Param("query") P p);


}
