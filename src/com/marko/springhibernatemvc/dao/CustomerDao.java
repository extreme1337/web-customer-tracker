package com.marko.springhibernatemvc.dao;

import com.marko.springhibernatemvc.entity.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);
    public Customer getCustomer(int theId);
}
