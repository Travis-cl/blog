package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Type
 * @Description TODO
 * @Author valkyreenv
 * Date 2020/4/14 17:50
 * Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();//oneToMany
}
