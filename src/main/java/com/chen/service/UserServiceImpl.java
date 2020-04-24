package com.chen.service;

import com.chen.dao.UserDao;
import com.chen.pojo.User;
import com.chen.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*loginCheck
* controller调用service时传入前台表单提交的username和password
* service调用dao层方法是传入这两个参数获取数据库的值
* */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User loginCheck(String username, String password) {
        User user = userDao.queryUserByNameAndPassword(username, MD5Utils.code(password));

        return user;
    }
}
