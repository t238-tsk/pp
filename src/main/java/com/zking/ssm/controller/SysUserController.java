package com.zking.ssm.controller;

import com.zking.ssm.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController {

    @RequestMapping(value = "/login")
    public String login(SysUser sysUser, Model model){
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();
        String message = null;

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            e.printStackTrace();
            message="账号错误";
        }catch (IncorrectCredentialsException s){
            s.printStackTrace();
            message="密码错误";
        }catch (Exception es){
            es.printStackTrace();
            message="账号或密码错误";
        }
        if(null==message){
            return "index";
        }else{
            model.addAttribute(message);
            return "login";
        }


    }


    @RequestMapping(value = "/loginout")
    public String loginout(){

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";



    }


}
