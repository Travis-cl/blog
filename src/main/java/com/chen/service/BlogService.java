package com.chen.service;

import com.chen.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    /*后台*/
    //第一次进入后台博客管理页面查询所有博客
    List<Blog> getAllBlogs();
    //根据form表单参数，查询指定条件的博客
    List<Blog> getBlogList(Blog blog);

    //增删改
    int saveBlog(Blog blog);
    int updateBlog(Blog blog);
    int deleteBlog(Long id);

    //前往博客编辑页面根据id查询blog信息传给前端并显示
    Blog getBlogById(Long id);

    /*前台*/
    //前台博客展示
    List<Blog> getIndexBlog();

    //前台最新推荐
    List<Blog> getAllRecommendBlog();

    //全局搜索
    List<Blog> getSearchBlog(String query);

    //前台点击标题或图片或推荐博客标题跳转到博客详情页面
    Blog getDetailBlog(Long id);

    //分类页和标签页查询博客结果
    List<Blog> getIndexBlogByTypeId(Long typeId);
    List<Blog> getIndexBlogByTagId(Long tagId);

    //归档-时间线博客mapper
    Map<String,List<Blog>> archiveBlog();

    int countBlog();




}
