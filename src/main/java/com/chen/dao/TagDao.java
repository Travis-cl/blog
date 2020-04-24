package com.chen.dao;

import com.chen.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagDao {
    //方法经测试正常
    int saveTag(Tag tag);
    Tag getTag(Long id);
    Tag getTagByName(String name);
    List<Tag> getAllTag();
    List<Tag> getBlogTag();
    int updateTag(Tag tag);
    int deleteTag(Long id);
}
