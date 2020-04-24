package com.chen.service;

import com.chen.NotFoundException;
import com.chen.dao.BlogDao;
import com.chen.pojo.Blog;
import com.chen.pojo.BlogAndTag;
import com.chen.pojo.Tag;
import com.chen.util.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Blog> getAllBlogs() {
        return blogDao.getAllBlogs();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public Blog getDetailBlog(Long id) {
        Blog blog = blogDao.getDetailBlog(id);
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public List<Blog> getIndexBlogByTypeId(Long typeId) {
        return blogDao.getIndexBlogByTypeId(typeId);
    }

    @Override
    public List<Blog> getIndexBlogByTagId(Long tagId) {
        return blogDao.getIndexBlogByTagId(tagId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();

        Set<String> set = new HashSet<>(years);
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set){
            map.put(year,blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.getAllBlogs().size();
    }

    @Override
    public List<Blog> getBlogList(Blog blog) {
        return blogDao.getBlogList(blog);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(blog);
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }
}
