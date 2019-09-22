package com.marko.springhibernatemvc.service;

import com.marko.springhibernatemvc.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();
    public Customer getCustomer(int theId);

    public void saveCustomer(Customer customer);
}
