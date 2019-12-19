package com.zking.ssm.service.impl;

import com.zking.ssm.mapper.BookMapper;
import com.zking.ssm.mapper.MyFileMapper;
import com.zking.ssm.model.Book;
import com.zking.ssm.model.MyFile;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private MyFileMapper myFileMapper;
    @Override
    public int deleteByPrimaryKey(Integer bookId) {
        return 0;
    }

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }

    @Override
    public int insertSelective(Book record) {
        return 0;
    }

    @Override
    public Book selectByPrimaryKey(Integer bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return bookMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Book record) {

        return bookMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Book> listBookByPager(Book book, PageBean pageBean) {
        return bookMapper.listBookByPager(book);
    }

    @Override
    public int upImg(Book book) {
        return bookMapper.upImg(book);
    }

    @Transactional
    @Override
    public int updImgs(Book book, MyFile myFile) {
        //1.新增文件上传记录
        myFileMapper.insert(myFile);
        //2.修改书本的img
        bookMapper.upImg(book);
        return 1;
    }

    @Override
    public List<Book> listBook(Book book) {
        return bookMapper.listBook(book);
    }

    @Override
    public List<Map> GroupBook() {
        return bookMapper.GroupBook();
    }
}
