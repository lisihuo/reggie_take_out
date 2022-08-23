package com.liyi.reggie.mapper;

import com.github.pagehelper.PageInfo;
import com.liyi.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 查询员工信息
     */
    List<Employee> queryEmployeeByLimit(int page, int pageSize,String username);

    /**
     * 查询员工信息进行登录校验
     */
    Employee queryEmployeeByUserName(String userName);

    /**
     * 新增员工
     */
    void save(Employee employee);
}
