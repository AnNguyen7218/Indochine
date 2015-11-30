/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.OrderedService;
import java.util.List;

/**
 *
 * @author Ashley
 */
public class OrderSerEntityManager extends AbstractEntityManager<OrderedService> {

    public OrderSerEntityManager() {
        super(OrderedService.class);
    }

    /**
     * Get all object
     *
     * @return List<Product> : list of OrderedService object
     */
    public List<OrderedService> getAllFromDB() {
        List<OrderedService> list = super.getAll();
        return list;
    }

    /**
     * Find object by name
     *
     * @param name
     * @return found OrderedService object
     */
    public OrderedService find(String name) {
        for (OrderedService ins : getAll()) {
            if (String.valueOf(ins.getId()).equalsIgnoreCase(name)) {
                return ins;
            }
        }

        System.err.println("Khong tim thay \"" + name + "\"");
        return null;
    }

    /**
     * Add new object which check validity
     *
     * @param instance
     * @return True:successfully added / False: add failed.
     */
    public boolean addNew(OrderedService instance) {
        if (find(String.valueOf(instance.getId())) != null) {
            return false;
        }

        if (super.insert(instance)) {
            DiaryEntityManager.createLog("Created product \"" + String.valueOf(instance.getId()) + "\"");
            return true;
        } else {
            System.out.println("Failed add order table");
            return false;
        }
    }



    /**
     * Disable of an object
     *
     * @param instance
     * @return True:successfully setted / False: set failed.
     */
    @Override
    public boolean delete(OrderedService instance) {
        try {
           
            if (super.delete(instance)) {
                DiaryEntityManager.createLog("Deleted order table \"" + String.valueOf(instance.getId()) + "\"");
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Failed to disable instance: " + ex.getMessage());
            return false;
        }
    }

    
}
