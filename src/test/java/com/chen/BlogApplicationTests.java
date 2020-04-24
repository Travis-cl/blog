package com.chen;

import com.chen.dao.TagDao;
import com.chen.pojo.Blog;
import com.chen.pojo.Tag;
import com.chen.pojo.Type;
import com.chen.service.BlogService;
import com.chen.service.TagService;
import com.chen.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @Test
    void contextLoads() {
        Blog blog = new Blog();
//        blog.setTitle("MVC");
        blog.setTypeId((long) 1);
        List<Blog> blogList = blogService.getBlogList(blog);
        for (Blog blog1 : blogList) {
            System.out.println(blog1);
        }

    }

}
