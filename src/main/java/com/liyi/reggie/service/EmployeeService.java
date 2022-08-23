package com.liyi.reggie.service;

import com.github.pagehelper.PageInfo;
import com.liyi.reggie.entity.Employee;
import com.liyi.reggie.mapper.EmployeeMapper;
import com.liyi.reggie.utils.SnowFlakeUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName EmployeeService
 * @Description 员工Service
 * @Author liyi
 * @Date 2022/08/23 9:38
 * @Version 1.0
 */
@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> queryEmployeeByLimit(int page, int pageSize,String username) {

        return employeeMapper.queryEmployeeByLimit(page,pageSize,username);
    }

    public Employee queryEmployeeByUserName(String userName) {
        return employeeMapper.queryEmployeeByUserName(userName);
    }

    public void save(HttpServletRequest request, Employee employee) {
        Long createUser = (Long)request.getSession().getAttribute("employee");
        Long updateUser = createUser;
        Long id = SnowFlakeUtils.getId();
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        LocalDateTime date = LocalDateTime.now();

        employee.setId(id);
        employee.setPassword(password);
        employee.setStatus(1);
        employee.setCreateUser(createUser);
        employee.setUpdateUser(updateUser);
        employee.setCreateTime(date);
        employee.setUpdateTime(date);

        employeeMapper.save(employee);
    }
}
