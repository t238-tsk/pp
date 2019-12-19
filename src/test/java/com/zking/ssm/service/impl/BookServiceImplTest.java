package com.zking.ssm.service.impl;

import com.zking.ssm.model.Book;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.util.PageBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class BookServiceImplTest {

    @Autowired
    private IBookService bookService;
    Book book;
    @Before
    public void setUp() throws Exception {
        book = new Book();
    }


    @Test
    public void listBookByPager() {
        PageBean pageBean = new PageBean();
        List<Book> books = bookService.listBookByPager(book,pageBean);
        for (Book book1 : books) {
            System.out.println(book1);
        }
        System.out.println("总记录数="+pageBean.getTotal());

    }

    @Test
    public void selectByPrimaryKey() {

        Book book1 = bookService.selectByPrimaryKey(5);
        System.out.println(book1);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        Book book2 = bookService.selectByPrimaryKey(5);
        System.out.println(book2);
    }



}