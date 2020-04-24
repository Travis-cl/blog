package com.chen.web;


import com.chen.pojo.Blog;
import com.chen.pojo.Tag;
import com.chen.pojo.Type;
import com.chen.service.BlogService;
import com.chen.service.TagService;
import com.chen.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model) {
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogService.getIndexBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);

        List<Type> blogType = typeService.getBlogType();
        model.addAttribute("types",blogType);

        List<Tag> blogTag = tagService.getBlogTag();
        model.addAttribute("tags",blogTag);

        List<Blog> allRecommendBlog = blogService.getAllRecommendBlog();
        model.addAttribute("recommendBlogs",allRecommendBlog);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, String query,Model model){

        PageHelper.startPage(pageNum,5);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blogs/{id}")
    public String toBlog(@PathVariable Long id, Model model) {
        Blog blogById = blogService.getDetailBlog(id);
        model.addAttribute("blog",blogById);
        return "blog";
    }

}
