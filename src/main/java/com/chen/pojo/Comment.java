package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Comment
 * @Description TODO
 * @Author valkyreenv
 * Date 2020/4/14 17:51
 * Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private boolean adminComment;  //是否为管理员评论
    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;  //父评论id
    private String parentNickname;

    //回复评论
    //private List<Comment> replyComments = new ArrayList<>();

    //父评论
    private Comment parentComment;

    private Blog blog;
}
