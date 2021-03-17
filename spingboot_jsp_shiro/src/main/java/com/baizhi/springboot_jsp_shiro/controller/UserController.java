package com.baizhi.springboot_jsp_shiro.controller;

import com.baizhi.springboot_jsp_shiro.entity.User;
import com.baizhi.springboot_jsp_shiro.service.UserService;
import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /*
    *用来处理身份验证
    * @param username
    * @param password
    * @return
     */
    /*
    * 用户认证
    * */


    /*
    * 用户注册
    * */
    @PostMapping("register")
    public String register(User user){
        User existuser=userService.findByUserName(user.getUsername());//查询是否存在同名用户
        if(existuser!=null)//如果已经存在，返回注册界面
        {
            return "redirect:/regist.jsp";
        }
        try{
            userService.register(user);
            return "redirect:/login.jsp";
        }catch (Exception e){
             e.printStackTrace();
             return "redirect:/regist.jsp";
        }
    }

    //退出登录
    @RequestMapping("logout")
    public String logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();//退出用户
        return "redirect:/login.jsp";
    }
    @RequestMapping("login")
    public String login(String username,String password){
        //获取主体对象
        Subject subject= SecurityUtils.getSubject();
    try {
        subject.login(new UsernamePasswordToken(username,password));
        return "redirect:/index.jsp";
    }catch (UnsupportedOperationException e){
        e.printStackTrace();
        System.out.println("用户名错误");
    }catch (IncorrectCredentialsException e){
        e.printStackTrace();
        System.out.println("密码错误");
    }
    return "redirect:/login.jsp";
    }
}
