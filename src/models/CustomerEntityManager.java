/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Customers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lehai
 */
public class CustomerEntityManager extends AbstractEntityManager<Customers> {

    public CustomerEntityManager() {
        super(Customers.class);
    }

    /**
     * Get all of the customer records from the database.
     *
     * @return list of customers
     */
    @Override
    public List<Customers> getAll() {
        List<Customers> list = super.getAll();

       return list;
    }

    
    /**
     * Search for a customer by his name.
     *
     * @param name
     * @return the found customer
     */
    public Customers find(String name) {
        for (Customers ins : getAll()) {
            if (ins.getCustomerName().equalsIgnoreCase(name)) {
                return ins;
            }
        }
        return null;
    }

    
     
    @Override
    public boolean insert(Customers instance) {
         if (instance.getCustomerName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getCustomerName()) != null) {
                System.err.println("Customer is already exists!");
                return false;
            }
            

            if (super.insert(instance)) {
                DiaryEntityManager.createLog("Added new Customer \"" + instance.getCustomerName() + "\"");
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Failed attempt to add new instance: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Update a customer's information
     *
     * @param instance new information
     * @return true if succeeded, else return false
     */
    @Override
    public boolean update(Customers instance) {
        if (instance.getCustomerName().isEmpty()) {
            return false;
        }

        try {
            Customers search = find(instance.getCustomerName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getCustomerId(), instance.getCustomerId())) {
                super.update(instance);
                DiaryEntityManager.createLog("Edited Customer \"" + instance.getCustomerName()+ "\"");
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Failed to update Customer: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Disable a customer
     *
     * @param instance
     * @return true if succeeded, else return false
     */
    @Override
    public boolean delete(Customers instance) {
        try {
           
            if (super.delete(instance)) {
                DiaryEntityManager.createLog("Deleted category \"" + instance.getCustomerName()+ "\"");
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Failed to disable category: " + ex.getMessage());
            return false;
        }
    }

   
}
