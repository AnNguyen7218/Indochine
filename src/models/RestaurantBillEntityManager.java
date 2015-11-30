/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.RestaurantBill;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashley
 */
public class RestaurantBillEntityManager extends AbstractEntityManager<RestaurantBill> {

    public RestaurantBillEntityManager() {
        super(RestaurantBill.class);
    }

    /**
     * Get all object in active status (isActive=true)
     *
     * @return List list of Order object is active
     */
    @Override
    public List<RestaurantBill> getAll() {
        List<RestaurantBill> list = super.getAll();
        return list;
    }

    /**
     * Find object by id
     *
     * @param id
     * @return found Order object
     */
    public RestaurantBill find(int id) {
        return super.find(id);
    }

    /**
     * Add new object
     *
     * @param instance
     * @return True:successfully added / False: add failed.
     */
    public boolean addNew(RestaurantBill instance) {

        if (super.insert(instance)) {
            DiaryEntityManager.createLog("Created bill #" + instance.getId());
            return true;
        } else {
            return false;
        }

    }
}
