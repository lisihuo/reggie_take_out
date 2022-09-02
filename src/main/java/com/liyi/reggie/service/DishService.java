package com.liyi.reggie.service;

import com.liyi.reggie.entity.Dish;
import com.liyi.reggie.mapper.DishMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DishService
 * @Description 菜品列表
 * @Author liyi
 * @Date 2022/9/2 14:36
 * @Version 1.0
 */
@Service
public class DishService {

    @Resource
    private DishMapper dishMapper;

    public List<Dish> queryAllDish( String name) {
       return dishMapper.queryAllDish(name);
    }
}
