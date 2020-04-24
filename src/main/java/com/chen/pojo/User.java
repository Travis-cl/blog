package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author valkyreenv
 * Date 2020/4/14 17:54
 * Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String email;
    private String avatar;
    private Integer type;

    private Date createTime;
    private Date updateTime;

    private List<Blog> blogs = new ArrayList<>();//oneToMany

}
