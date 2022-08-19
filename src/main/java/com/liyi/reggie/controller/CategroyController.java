package com.liyi.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Category;
import com.liyi.reggie.mapper.CategoryMapper;
import com.liyi.reggie.service.CategoryService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategroyController {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类管理查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize) {

        //创建分页构造器
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);

        categoryMapper.selectPage(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 新增菜品
     */
    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Category category) {

        log.info("category:{}", category);

        Date date = new Date();
        category.setCreateTime(date);
        category.setUpdateTime(date);
        Long empId = (Long) request.getSession().getAttribute("employee");
        category.setCreateUser(empId);
        category.setUpdateUser(empId);
        categoryService.save(category);

        return Result.success("新增菜品成功");
    }

    /**
     * 修改菜品信息
     *
     * @param category
     * @return
     */
    @PutMapping
    public Result<String> modify(HttpServletRequest request, @RequestBody Category category) {
        log.info("category:{}", category);

        Long empId = (Long) request.getSession().getAttribute("employee");
        category.setUpdateUser(empId);
        categoryService.updateById(category);
        return Result.success("菜品信息修改成功");
    }

    /**
     * 删除菜品信息
     *
     * @param
     * @return
     */
    @DeleteMapping
    public Result<String> delete(Long ids) {

        log.info("ids:{}", ids);

        categoryMapper.deleteById(ids);
        //categoryService.removeById(ids);
        return Result.success("菜品删除成功");
    }
}
