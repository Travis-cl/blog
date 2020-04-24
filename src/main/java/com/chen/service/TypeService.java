package com.chen.service;

import com.chen.pojo.Type;


import java.util.List;

public interface TypeService {
    /*后台功能*/
    //新增-更新-删除
    int saveType(Type type);
    int updateType(Type type);
    int deleteType(Long id);
    //根据id查询type--进入编辑页面时传入首页需要展示的typeid
    Type getType(Long id);
    //新增或保存时，获取前台传入的typename，判断是否已经存在这个type
    Type getTypeByName(String name);
    //进入type管理页面时查询所有type
    List<Type> getAllType();


    /*前台功能*/
    //首页右侧展示博客数量
    List<Type> getBlogType();






}
