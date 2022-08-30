package com.liyi.reggie.controller;

import com.github.pagehelper.PageInfo;
import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Category;
import com.liyi.reggie.entity.Employee;
import com.liyi.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategroyController {

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
    public Result<PageInfo<Category>> queryCateoryAll(int page, int pageSize) {
        log.info("page:{},pageSize:{}",page,pageSize);
        List<Category> categoryList = categoryService.queryCateoryAll(page, pageSize);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);

        return Result.success(pageInfo);
    }

    /**
     * 新增菜品
     * @param request
     * @param category
     * @return
     */
    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Category category) {
        log.info("category:{}", category);

        categoryService.addCategory(request,category);

        return null;
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

        return null;
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

        return null;
    }
}
