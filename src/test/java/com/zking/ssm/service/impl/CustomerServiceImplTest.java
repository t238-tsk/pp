package com.zking.ssm.service.impl;

import com.zking.ssm.model.Customer;
import com.zking.ssm.service.ICustomerService;
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
public class CustomerServiceImplTest {
    Customer customer;
    @Autowired
    private ICustomerService customerService;
    @Before
    public void setUp() throws Exception {
        customer = new Customer();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() {
        Customer customer1 = customerService.selectByPrimaryKey(1);
        System.out.println(customer1);
    }
}