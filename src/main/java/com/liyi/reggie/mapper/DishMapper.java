package com.liyi.reggie.mapper;

import com.liyi.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper{

    /**
     *
     */
    List<Dish> queryAllDish(String name );
}
