package com.zking.ssm.controller;

import com.zking.ssm.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;


@Controller

public class HelloController {

    @RequestMapping(value = "/hello1")
    public String hello1(@RequestParam String name, Book book,String username, HttpServletRequest request,@RequestParam Map map){
        System.out.println("hello..........");
        System.out.println("name="+name);
        System.out.println("book="+book);
        System.out.println("map="+map);
        System.out.println("request"+request);
        return "index";
    }
    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("hello..........");

        return "login";
    }
    @RequestMapping(value = "/uploads")
    public String uploads(){
        System.out.println("hello..........");

        return "uploads";
    }
    @RequestMapping(value = "/downupload")
    public String downupload(){
        System.out.println("hello..........");

        return "downupload";
    }
    @RequestMapping(value = "/face")
    public String face(){
        System.out.println("face..........");

        return "face";
    }
    @RequestMapping(value = "/hello2")
    public ModelAndView hello2(@RequestParam String name, Book book,String username, HttpServletRequest request, @RequestParam Map map){
        System.out.println("hello..........");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("name",name);
        modelAndView.addObject("book",book);
        return modelAndView;
    }

    @RequestMapping(value = "/hello3")
    public String hello3(@RequestParam String name, Book book,String username, HttpServletRequest request, @RequestParam Map map,Model model){
        System.out.println("hello..........");
        model.addAttribute("map",map);
        return "index";
    }

    @RequestMapping(value = "/hello4")
    public String hello4(@RequestParam String name, Book book,String username, HttpServletRequest request, @RequestParam Map map,Model model){
        System.out.println("hello..........");
        request.setAttribute("username",username);
        return "index";
    }

    //转发
    @RequestMapping(value = "/hello5")
    public String hello5(@RequestParam String name, Book book,String username, HttpServletRequest request, @RequestParam Map map,Model model){
        System.out.println("hello..........");
        request.setAttribute("username",username);
        model.addAttribute("map",map);
        return "forward:toIndex";

    }
    @RequestMapping(value = "/toIndex")
    public String toIndex(){
        System.out.println("toIndex.......");
        return "index";//逻辑视图名
    }

    //重定向
    @RequestMapping(value = "/hello6")
    public String hello6(@RequestParam String name, Book book,String username, HttpServletRequest request, @RequestParam Map map,Model model){
        System.out.println("hello..........");
        request.setAttribute("username",username);
        model.addAttribute("map",map);
        return "redirect:toIndex1";

    }
    @RequestMapping(value = "/toIndex1")
    public String toIndex1(){
        System.out.println("toIndex.......");
        return "index";//逻辑视图名
    }


    @RequestMapping(value = "/hello7")
    @ResponseBody
    public Book hello7(@RequestParam String name, Book book,String username, HttpServletRequest request, @RequestParam Map map,Model model){
        System.out.println("hello..........");
        request.setAttribute("username",username);
        return book;
    }

    @RequestMapping(value = "/change")
    public String change(@RequestParam String locale, HttpSession session){

        System.out.println("change.......");
    if("zh".equals(locale)){
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);

    }else{
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);

    }
        return "index";//逻辑视图名
    }
}

