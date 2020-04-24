package com.chen.dao;

import com.chen.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserDao {
    User queryUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

}
