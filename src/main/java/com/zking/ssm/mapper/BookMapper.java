package com.zking.ssm.mapper;

import com.zking.ssm.model.Book;
import com.zking.ssm.model.MyFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    //修改书本信息（有判断，如果书本属性值不为Null，那么修改书本信息）
    int updateByPrimaryKeySelective(Book record);
    //修改书本信息
    int updateByPrimaryKey(Book record);

    List<Book> listBookByPager(Book book);

    int upImg(Book book);

    List<Book> listBook(Book book);

    List<Map> GroupBook();

}