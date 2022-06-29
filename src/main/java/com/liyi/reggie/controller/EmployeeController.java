package com.liyi.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Employee;
import com.liyi.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {


        //将前端传给的密码进行MD5编码
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据用户名查询数据库(用户名是索引，唯一)
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(wrapper);

        //判断是否用户名是否为空
        if (emp == null) {
            return Result.error("用户名不存在");
        }

        //判断密码是否正确
        if (!password.equals(emp.getPassword())) {

            return Result.error("登录失败，密码不正确");
        }

        //判断状态是否禁用
        if (emp.getStatus() == 0) {
            return Result.error("该用户状态已禁用");
        }

        //创建session
        HttpSession session = request.getSession();
        session.setAttribute("employee", emp.getId());

        return Result.success(emp);
    }


    /**
     * 登录退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {

        request.getSession().removeAttribute("employee");
        return Result.success("退出成功");
    }


    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Employee employee) {

        //密码使用md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return Result.success("新增员工成功");
    }
}
