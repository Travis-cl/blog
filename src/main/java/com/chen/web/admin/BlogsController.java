package com.chen.web.admin;

import com.chen.pojo.Blog;
import com.chen.pojo.User;
import com.chen.service.BlogService;
import com.chen.service.TagService;
import com.chen.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class BlogsController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());

    }

    //第一次进入博客页面进行的查询。
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pageNum") int pageNum,
                        Model model){
        PageHelper.startPage(pageNum,5);
        List<Blog> blogList = blogService.getAllBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }
    //使用form表单删选条件进行的查询
    @PostMapping("/blogs/search")
    public String searchBlogs(@RequestParam(required = false,defaultValue = "1",value = "pageNum") int pageNum,
                              Blog blog, Model model){
        PageHelper.startPage(pageNum,5);
        System.out.println(blog);
        List<Blog> blogList = blogService.getBlogList(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("msg","查询成功");
        setTypeAndTag(model);
        return "admin/blogs";
    }
    //前往博客发布页面
    @GetMapping("/blogs/input")
    public String toAddBlogPage(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
    //前往博客编辑页面（带参数id）
    @GetMapping("/blogs/{id}/input")
    public String toEditPage(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogById(id);
        blog.init();
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //发布或更新博客
    @PostMapping("/blogs")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes){
        //设置user属性
        blog.setUser((User) session.getAttribute("sessionUser"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));


        if (blog.getId() == null){
            blogService.saveBlog(blog);
            attributes.addFlashAttribute("msg","新增成功");
        }else {
            blogService.updateBlog(blog);
            attributes.addFlashAttribute("msg","更新成功");
        }


        return "redirect:/admin/blogs";
    }
    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id,RedirectAttributes attributes){

        int i = blogService.deleteBlog(id);
        if (i != 0){
            attributes.addFlashAttribute("msg","删除成功");
        }else {
            attributes.addFlashAttribute("msg","删除失败");
        }

        return "redirect:/admin/blogs";
    }
}
