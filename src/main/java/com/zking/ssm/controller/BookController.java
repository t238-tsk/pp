package com.zking.ssm.controller;

import com.zking.ssm.model.Book;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "book")
public class BookController {
    @Autowired
    private IBookService bookService;
    @ModelAttribute
    public void init(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
    }

    @RequestMapping(value = "/toAdd")
    public String toAdd(){
            return "book/bookAdd";
    }
    //新增书本
    @RequestMapping( value = "/addBook")
    public String addBook(@Validated(value = Book.ValidateGroups.Add.class) @ModelAttribute Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/bookAdd";
        }else{
            int i = bookService.insert(book);
            String s="";
            if(i>0){
                s = "index";
            }else{
                s = "book/bookAdd";
            }

            return s;
        }

    }
    //修改书本信息
    @RequestMapping( value = "/updBook")
    public String updBook(@Validated(value = Book.ValidateGroups.Edit.class) @ModelAttribute Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/bookAdd";
        }else{
            int i = bookService.updateByPrimaryKeySelective(book);
            String s="";
            if(i>0){
                s = "index";
            }else{
                s = "book/bookAdd";
            }

            return s;
        }

    }
    //查询所有书本信息
    @RequestMapping(value = "/bookList")
    public ModelAndView bookList(Book book, HttpServletRequest request){
        PageBean pageBean = new PageBean();
        pageBean.initPageBean(request,pageBean);
        List<Book> books = bookService.listBookByPager(book, pageBean);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book/listBook");
        modelAndView.addObject("books",books);
        modelAndView.addObject("pageBean",pageBean);
       return modelAndView;
    }

    //查询所有书本信息
    @RequestMapping(value = "/bookList2")
    @ResponseBody
    public List<Book> bookList2(Book book, HttpServletRequest request){

        List<Book> books = bookService.listBook(book);

        return books;
    }


}
