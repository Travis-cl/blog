package com.chen.service;

import com.chen.pojo.Tag;
import java.util.List;

public interface TagService {
    /*后台*/
    //增删改
    int saveTag(Tag tag);
    int updateTag(Tag tag);
    int deleteTag(Long id);

    //进入tag编辑页面根据前台传入的id查询tag传递给前台并显示
    Tag getTag(Long id);
    //更新或新增时根据传入的name查询后台是否已存在
    Tag getTagByName(String name);
    //进入tag管理页面查询所有tag
    List<Tag> getAllTag();
    //blog页面的新增或更新提交时，将text字符串转换成list<tags>
    List<Tag> getTagByString(String text);


    /*前台*/
    //首页右侧显示标签数量
    List<Tag> getBlogTag();
}
