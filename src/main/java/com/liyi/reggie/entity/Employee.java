package com.liyi.reggie.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * 员工表
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;  // 身份证号

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

}
