package com.chen.dao;

import com.chen.pojo.Blog;
import com.chen.pojo.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
    List<Blog> getAllBlogs();

    Blog getBlogById(Long id);

    List<Blog> getBlogList(Blog blog);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    List<Blog> getIndexBlog();

    int saveBlogAndTag(BlogAndTag blogAndTag);

    List<Blog> getAllRecommendBlog();

    List<Blog> getSearchBlog(String query);

    Blog getDetailBlog(Long id);

    List<Blog> getIndexBlogByTypeId(@Param("typeId") Long typeId);

    List<Blog> getIndexBlogByTagId(@Param("tagId") Long tagId);

    List<String> findGroupYear();

    List<Blog> findByYear(@Param("year") String year);


}
