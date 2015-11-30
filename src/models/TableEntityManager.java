/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.RestaurantTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class TableEntityManager extends AbstractEntityManager<RestaurantTable> {

    public TableEntityManager() {
        super(RestaurantTable.class);
    }

    /**
     * Get all of the customer records from the database.
     *
     * @return list of customers
     */
    @Override
    public List<RestaurantTable> getAll() {
        List<RestaurantTable> list = super.getAll();

        List<RestaurantTable> res = new ArrayList<>();

        //Filter out those disabled customers
        for (RestaurantTable ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    public List<RestaurantTable> getAllFromDB() {
        List<RestaurantTable> list = super.getAll();

        return list;
    }

    /**
     * Search for a customer by his name.
     *
     * @param name
     * @return the found customer
     */
    public RestaurantTable find(int id) {
        for (RestaurantTable ins : getAll()) {
            if (ins.getTableId() == id) {
                return ins;
            }
        }
        return null;
    }

   
    /**
     * Update a customer's information
     *
     * @param instance new information
     * @return true if succeeded, else return false
     */
    @Override
    public boolean update(RestaurantTable instance) {
        if (instance.getTableId() == null) {
            return false;
        }

        try {
            RestaurantTable search = find(instance.getTableId());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getTableId(), instance.getTableId())) {
                super.update(instance);
                DiaryEntityManager.createLog("Edited Customer \"" + instance.getTableId() + "\"");
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
    public boolean delete(RestaurantTable instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted category \"" + instance.getTableId() + "\"");
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
