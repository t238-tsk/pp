package com.zking.ssm.controller;

import com.zking.ssm.model.Book;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.util.PageBean;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private IBookService bookService;
    @ModelAttribute
    public void init(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
    }





    //查询所有书本信息

    @RequestMapping(value = "/bookList2")
    public List<Book> bookList2(Book book, HttpServletRequest request){

        List<Book> books = bookService.listBook(book);

        return books;
    }

    //查询所有书本信息
    @RequiresRoles(value = "管理员")
    @RequestMapping(value = "/bookGroup")
    public List<Map> bookGroup(){
        List<Map> maps = bookService.GroupBook();

        return maps;
    }

}
