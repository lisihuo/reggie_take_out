package com.liyi.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Employee;
import com.liyi.reggie.mapper.EmployeeMapper;
import com.liyi.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

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


    /**
     * 显示员工信息列表
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name) {

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造查询条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(name), Employee::getUsername, name);

        //结果排序
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);
    }


    /**
     * 修改员工状态
     *
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    public Result<String> update(HttpServletRequest request, @RequestBody Employee employee) {

        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(empId);
        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);

        return Result.success("员工状态修改成功");
    }


    /**
     * 根据url传值的id，查询员工信息，回显到页面
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result<Employee> edit(@PathVariable Long id) {

        Employee employee = employeeMapper.selectById(id);

        return Result.success(employee);
    }
}
