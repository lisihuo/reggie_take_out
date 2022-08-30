package com.liyi.reggie.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 分类管理信息表
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer type;

    private String name;

    private int sort;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Long createUser;

    private Long updateUser;

}
