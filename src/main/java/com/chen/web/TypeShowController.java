package com.chen.web;

import com.chen.pojo.Blog;
import com.chen.pojo.Type;
import com.chen.service.BlogService;
import com.chen.service.TypeService;
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
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        @PathVariable Long id, Model model) {

        List<Type> blogType = typeService.getBlogType();
        if (id == -1){
            id = blogType.get(0).getId();
        }

        PageHelper.startPage(pageNum,5);
        List<Blog> blogList = blogService.getIndexBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",blogType);

        model.addAttribute("activeTypeId",id);
        return "types";
    }


}
