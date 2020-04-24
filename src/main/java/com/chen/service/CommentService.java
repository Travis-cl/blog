package com.chen.service;

import com.chen.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    //获取这个博客下的所有comment
    List<Comment> listCommentByBlogId(Long blogId);
    //保存新评论
    int saveComment(Comment comment);
}
