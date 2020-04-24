package com.chen.service;

import com.chen.dao.CommentDao;
import com.chen.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;

    //查询父评论
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {

        List<Comment> comments = commentDao.listCommentByBlogId(blogId,Long.parseLong("-1"));
        return comments;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        //获得父id
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
        }else {
            comment.setParentCommentId((long) -1);
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }
}
