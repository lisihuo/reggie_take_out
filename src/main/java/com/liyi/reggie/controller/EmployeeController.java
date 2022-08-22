package com.liyi.reggie.controller;

import com.liyi.reggie.common.Result;
import com.liyi.reggie.entity.Employee;
import com.liyi.reggie.mapper.EmployeeMapper;
import com.liyi.reggie.utils.MybatisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee emp = employeeMapper.queryEmployeeByUserName(employee.getUsername());


        //将前端传给的密码进行MD5编码
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

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

        sqlSession.close();

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

       return null;
    }


    @GetMapping("/page")
    public Result<List<Employee>> page(int page, int pageSize) {
        log.info("page:{},pageSize:{}",page,pageSize);

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryEmployeeByLimit(page,pageSize);
        return Result.success(list);
    }
}
