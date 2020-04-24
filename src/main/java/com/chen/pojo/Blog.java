package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Blog
 * @Description TODO
 * @Author valkyreenv
 * Date 2020/4/14 17:40
 * Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;//赞赏
    private boolean shareStatement;//分享
    private boolean commentTable;//评论
    private boolean published;//发布
    private boolean recommend;//推荐
    private Date createTime;
    private Date updateTime;

    //这个属性是在mybatis中进行连接查询
    private Long typeId;
    private Long userId;

    //获取多个标签的id
    private String tagIds;
    private String description;

    //维护关系
    private Type type;//manyToOne
    private List<Tag> tags = new ArrayList<>();//manyToMany
    private User user;//manyToOne
    private List<Comment> comments = new ArrayList<>();//oneToMany

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
