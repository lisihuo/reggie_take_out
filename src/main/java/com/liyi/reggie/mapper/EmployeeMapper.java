package com.liyi.reggie.mapper;

import com.liyi.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 查询员工信息
     */
    List<Employee> queryEmployeeByLimit(int page, int pageSize);

    /**
     * 查询员工信息进行登录校验
     */
    Employee queryEmployeeByUserName(String userName);
}
