package com.chen.dao;

import com.chen.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeDao {
    //添加，添加完成后返回这个Type
    int saveType(Type type);

    //根据id查询type
    Type getType(Long id);
    Type getTypeByName(String name);
    List<Type> getAllType();

    //首页右侧展示博客数量
    List<Type> getBlogType();


    int updateType(Type type);
    int deleteType(Long id);

}
