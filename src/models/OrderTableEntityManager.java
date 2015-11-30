/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.OrderTable;
import entities.OrderTable;
import entities.OrderTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class OrderTableEntityManager extends AbstractEntityManager<OrderTable> {

    public OrderTableEntityManager() {
        super(OrderTable.class);
    }

    /**
     * Get all object
     *
     * @return List<Product> : list of OrderTable object
     */
    public List<OrderTable> getAllFromDB() {
        List<OrderTable> list = super.getAll();
        return list;
    }

    /**
     * Find object by name
     *
     * @param name
     * @return found OrderTable object
     */
    public OrderTable find(String name) {
        for (OrderTable ins : getAll()) {
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
    public boolean addNew(OrderTable instance) {
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

//    /**
//     * Update an object, with check exists name ID of updated object should be
//     * exist in database, if not, it will create new one.
//     *
//     * @param instance
//     * @param name
//     */
//    public boolean edit(OrderTable instance) {
//        try {
//            OrderTable search = find(String.valueOf(instance.getId()));
//            //Neu doi ten thi phai bao dam la ten nay chua ton tai
//            if (search == null || Objects.equals(search.getId(), instance.getId())) {
//                
//                super.update(instance);
//                DiaryEntityManager.createLog("Edited order table \"" + String.valueOf(instance.getId()) + "\"");
//                return true;
//            }
//
//            return false;
//
//        } catch (Exception ex) {
//            System.out.println("Update product khong thanh cong: " + ex.getMessage());
//            return false;
//        }
//    }

    /**
     * Disable of an object
     *
     * @param instance
     * @return True:successfully setted / False: set failed.
     */
    @Override
    public boolean delete(OrderTable instance) {
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
