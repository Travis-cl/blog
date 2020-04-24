package com.chen.dao;

import com.chen.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface CommentDao {
    //获取这个博客下的所有comment
    List<Comment> listCommentByBlogId(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);
    //保存新评论
    int saveComment(Comment comment);
    //如有是回复的评论，则查找出父评论的内容
    Comment findByParentCommentId(@Param("parentCommentId") Long parentCommentId);
}
