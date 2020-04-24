package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BlogAndTag
 * @Description TODO
 * @Author valkyreenv
 * Date 2020/4/15 13:07
 * Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {

    private Long tagId;

    private Long blogId;
}
