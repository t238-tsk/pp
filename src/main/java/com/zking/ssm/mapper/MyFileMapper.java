package com.zking.ssm.mapper;

import com.zking.ssm.model.MyFile;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileMapper {


    int insert(MyFile record);



    MyFile selectByPrimaryKey(Long fileId);


}