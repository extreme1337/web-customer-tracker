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
    public List<Customer> searchCustomers(String theSearchName) {
        //get current session for hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        //only search for the name if theSearchName is not empty
        if(theSearchName != null && theSearchName.trim().length() > 0){
            //search for the first name or the last name case insensitive
            query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName ",Customer.class);
            query.setParameter("theName","%"+theSearchName+"%");
        }else {
            //search name is empty. Get all customers
            query = session.createQuery("from Customer", Customer.class);
        }
        //execute query and get customers
        List<Customer> customers = query.getResultList();

        return customers;
    }

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
