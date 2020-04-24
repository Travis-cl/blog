package com.chen.service;

import com.chen.pojo.User;

public interface UserService {

    //后台登录验证
    User loginCheck(String username,String password);

}
