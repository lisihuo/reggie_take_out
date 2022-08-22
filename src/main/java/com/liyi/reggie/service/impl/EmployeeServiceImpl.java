package com.liyi.reggie.service.impl;

import com.liyi.reggie.entity.Employee;
import com.liyi.reggie.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeMapper {
    @Override
    public List<Employee> queryEmployeeByLimit(int page, int pageSize) {
        return null;
    }

    @Override
    public Employee queryEmployeeByUserName(String userName) {
        return null;
    }
}
