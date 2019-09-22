package com.marko.springhibernatemvc.controller;

import com.marko.springhibernatemvc.dao.CustomerDao;
import com.marko.springhibernatemvc.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    //need to inject the customer dao
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/list")
    public String listCustomers(Model theModel){
        //get customer from the dao
        List<Customer> theCustomers = customerDao.getCustomer();
        //add the customers to the model
        theModel.addAttribute("customers",theCustomers);
        return "list-customers";
    }
}
