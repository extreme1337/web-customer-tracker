package com.marko.springhibernatemvc.service;

import com.marko.springhibernatemvc.dao.CustomerDao;
import com.marko.springhibernatemvc.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{

    //need to inject customer dao
    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    @Transactional
    public List<Customer> getCustomer() {
        return customerDao.getCustomer();
    }
}
