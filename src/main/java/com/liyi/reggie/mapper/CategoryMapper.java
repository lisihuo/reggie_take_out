package com.liyi.reggie.mapper;

import com.liyi.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     *  查询菜品
     */
    List<Category> queryCateoryAll(int page , int pageSize);

    /**
     * 新增菜品
     * @param category
     * @return
     */
    void addCategoryType(Category category);

    /**
     * 删除菜品
     * @param ids
     */
    void deleteCategory(Long ids);

    /**
     * 修改菜品信息
     * @param category
     */
    int updateCategory(Category category);

}
