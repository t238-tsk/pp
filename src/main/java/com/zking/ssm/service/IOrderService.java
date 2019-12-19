package com.zking.ssm.service;

import com.zking.ssm.model.Order;
import org.springframework.stereotype.Repository;


public interface IOrderService {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}