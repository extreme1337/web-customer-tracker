package com.marko.springhibernatemvc.dao;

import com.marko.springhibernatemvc.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class CustomerDAOImpl implements CustomerDao {
    //need to inject to session factory
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<Customer> getCustomer() {
        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
        //create a query
        Query<Customer> theQuery = session.createQuery("from Customer ",Customer.class);
        //execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        //return the results
        return customers;
    }
}
