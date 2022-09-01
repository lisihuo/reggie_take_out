package com.liyi.reggie.service;

import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Category;
import com.liyi.reggie.mapper.CategoryMapper;
import com.liyi.reggie.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public List<Category> queryCateoryAll(int page ,int pageSize){

        List<Category> categoryList = categoryMapper.queryCateoryAll(page, pageSize);

        return categoryList;
    }

    public void addCategoryType(HttpServletRequest request, Category category) {
        Long id = SnowFlakeUtils.getId();
        LocalDateTime date = LocalDateTime.now();
        Long createUser = (Long)request.getSession().getAttribute("employee");
        Long updateUser = createUser;

        category.setId(id);
        category.setCreateTime(date);
        category.setUpdateTime(date);
        category.setCreateUser(createUser);
        category.setUpdateUser(updateUser);

        categoryMapper.addCategoryType(category);
    }

    public void deleteCategory(Long ids) {

        categoryMapper.deleteCategory(ids);
    }

    public void updateCategory(HttpServletRequest request, Category category) {
        Long updateUser = (Long) request.getSession().getAttribute("employee");
        String name = category.getName();
        LocalDateTime date = LocalDateTime.now();
        int sort = category.getSort();

        category.setUpdateUser(updateUser);
        category.setName(name);
        category.setUpdateTime(date);
        category.setSort(sort);

        categoryMapper.updateCategory(category);
    }
}
