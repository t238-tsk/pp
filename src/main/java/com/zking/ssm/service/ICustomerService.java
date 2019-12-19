package com.zking.ssm.service;

import com.zking.ssm.model.Customer;
import org.springframework.stereotype.Repository;


public interface ICustomerService {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}