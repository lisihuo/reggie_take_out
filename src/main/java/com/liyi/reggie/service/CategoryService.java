package com.liyi.reggie.service;

import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Category;
import com.liyi.reggie.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCateoryAll(int page ,int pageSize){

        List<Category> categoryList = categoryMapper.queryCateoryAll(page, pageSize);

        return categoryList;
    }

    public void addCategory(HttpServletRequest request, Category category) {

    }
}
