package com.liyi.reggie.mapper;

import com.liyi.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     *  查询菜品
     */
    List<Category> queryCateoryAll(int page , int pageSize);

}
