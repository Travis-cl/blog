package com.chen.web.admin;

import com.chen.pojo.User;
import com.chen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author valkyreenv
 * Date 2020/4/15 14:34
 * Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    //所有默认的admin请求跳转到login页面
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    //判断账号密码
    @PostMapping("/login")
    public String loginCheck(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session,
                             RedirectAttributes redirectAttributes){
        User user = userService.loginCheck(username, password);
        if (user != null){
            user.setPassword("");
            session.setAttribute("sessionUser",user);
            return "admin/index";
        } else {
            redirectAttributes.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/admin";
        }

    }

    //注销
    @GetMapping("/logout")
    public String logOut(HttpSession session){

        session.removeAttribute("sessionUser");
        return "redirect:/admin";
    }





}
