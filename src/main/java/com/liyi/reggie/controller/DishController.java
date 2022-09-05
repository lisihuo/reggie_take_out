package com.liyi.reggie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Dish;
import com.liyi.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;

    /**
     *  菜品列表
     */
    @ResponseBody
    @RequestMapping("/page")
    public Result<PageInfo<Dish>> queryAllDish(int page, int pageSize, String name){

        PageHelper.startPage(page, pageSize);
        List<Dish> dishes = dishService.queryAllDish(name);
        PageInfo<Dish> pageInfo = new PageInfo<>(dishes);

        return Result.success(pageInfo);
    }

    /**
     * 删除菜品
     * @return
     */
    @ResponseBody
    @DeleteMapping
    public Result<String> deleteDish(Long ids){
        dishService.deleteDish(ids);

        return Result.success("删除成功");
    }

    @ResponseBody
    @RequestMapping("/list/{type}")
    public Result<String> modifyDish(@PathVariable int type){


        return null;
    }

}
