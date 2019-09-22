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
    public void deleteCustomer(int theId) {
        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
        //delete object with primary key
        Query theQuery = session.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId",theId);
        theQuery.executeUpdate();
    }

    @Override
    public void saveCustomer(Customer customer) {
        //get current hibernate session
        Session session = sessionFactory.getCurrentSession();
        //save the customer
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
        //now retrieve/read from database using the primary key
        Customer customer = session.get(Customer.class,theId);
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
        //create a query sort by last name
        Query<Customer> theQuery = session.createQuery("from Customer order by lastName asc ",Customer.class);
        //execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        //return the results
        return customers;
    }
}
