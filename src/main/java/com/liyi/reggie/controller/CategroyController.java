package com.liyi.reggie.controller;

import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Category;
import com.liyi.reggie.entity.Employee;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 分类管理查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<List<Employee>> page(int page, int pageSize) {

        return null;
    }

    /**
     * 新增菜品
     */
    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Category category) {

        log.info("category:{}", category);

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
