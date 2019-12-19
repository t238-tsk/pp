package com.zking.ssm.service.impl;

import com.zking.ssm.model.Order;
import com.zking.ssm.service.IOrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class OrderServiceImplTest {
    Order order;
    @Autowired
    private IOrderService orderService;
    @Before
    public void setUp() throws Exception {
        order = new Order();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() {
        Order order1 = orderService.selectByPrimaryKey(9);
        System.out.println(order1);
    }
}