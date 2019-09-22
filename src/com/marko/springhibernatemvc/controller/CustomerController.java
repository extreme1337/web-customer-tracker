package com.marko.springhibernatemvc.controller;

import com.marko.springhibernatemvc.entity.Customer;
import com.marko.springhibernatemvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //need to inject customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel){
        //get customer from the service
        List<Customer> theCustomers = customerService.getCustomers();
        //add the customers to the model
        theModel.addAttribute("customers",theCustomers);
        return "list-customers";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute to bind form data
        Customer customer =new Customer();
        theModel.addAttribute("customer",customer);
        return "customer-form";
    }
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        //save the customer using our service
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,Model model){
        //get the customer from our service
        Customer customer = customerService.getCustomer(theId);
        //set customer as a model attribute to pre-populate the form
        model.addAttribute("customer",customer);
        //send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId){
        //delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }
}
