package com.chen.web;

import com.chen.pojo.Blog;
import com.chen.pojo.Tag;
import com.chen.service.BlogService;
import com.chen.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                       @PathVariable Long id, Model model) {
        PageHelper.startPage(pageNum,10000);
        List<Tag> allTag = tagService.getBlogTag();
        model.addAttribute("tags",allTag);
        if (id == -1){
            id = allTag.get(0).getId();
        }
        List<Blog> blogs = blogService.getIndexBlogByTagId(id);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId",id);

        return "tags";
    }

}
