package com.liyi.reggie.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜品实体类
 */
@Data
public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long categoryId;

    private double price;

    private String code;

    private String image;

    private String description;

    private int status;

    private int sort;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Long createUser;

    private Long updateUser;

    private int isDeleted;

}
