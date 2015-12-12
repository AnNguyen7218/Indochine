/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Functions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lehai
 */
public class ActionEntityManager extends AbstractEntityManager<Functions> {

    public ActionEntityManager() {
        super(Functions.class);
    }

    final public static int ACCOUNT_MANAGEMENT = 1;
    final public static int ACCOUNT_PERMISSION = 2;
    final public static int PRODUCT_MANAGEMENT = 3;
    final public static int RES_CHECKOUT = 4;
    final public static int BILL_OVERVIEW = 5;
    final public static int CHANGE_PASSWORD = 6;
    final public static int CATEGORY_MANAGEMENT = 7; //Res cate
    final public static int CUSTOMER_MANAGEMENT = 8;//Ser cus
    final public static int REPORT = 9;
    final public static int BACKUP = 10;
    final public static int RESTORE = 11;
    final public static int DIARY = 12;
    final public static int RES_SUPPLIER = 13;
    final public static int SER_ORDER = 14;
    final public static int TABLE=15;
    final public static int ROOM = 16;
    final public static int HOTEL_CHECKIN = 17;
    final public static int HOTEL_CHECKOUT = 18;
    final public static int HOTEL_CUS = 19;
    final public static int HOTEL_ORDER_SER = 20;
    
    /**
     * Get all object in active status (isActive=true)
     *
     * @return List<> : list of Functions object
     */
    @Override
    public List<Functions> getAll() {
        List<Functions> list = super.getAll();

        List<Functions> res = new ArrayList<>();

        for (Functions ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    /**
     * Get all object
     *
     * @return List : list of Functions object
     */
    public List<Functions> getAllFromDB() {
        List<Functions> list = super.getAll();
        return list;
    }

    /**
     * Find object by name
     *
     * @param name
     * @return found Functions object
     */
    public Functions find(String name) {
        for (Functions ins : getAll()) {
            if (ins.getFunctionName().equalsIgnoreCase(name)) {
                return ins;
            }
        }
        System.out.println("Function \"" + name + "\" chua ton tai");
        return null;
    }

    /**
     * Add new object
     *
     * @param instance
     * @return True:successfully added / False: add failed.
     */
    public boolean addNew(Functions instance) {
        if (instance.getFunctionName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getFunctionName()) != null) {
                System.err.println("Function da ton tai!");
                return false;
            }

            instance.setIsActive(true);
            super.insert(instance);
            DiaryEntityManager.createLog("Created function \"" + instance.getFunctionName() + "\"");
            return true;
        } catch (Exception ex) {
            System.out.println("Them function moi khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Update an object, with check exists name 
     * ID of updated object should be
     * exist in database, if not, it will create new one.
     *
     * @param instance
     * @param name
     */
    public boolean edit(Functions instance) {
        if (instance.getFunctionName().isEmpty()) {
            return false;
        }

        try {
            Functions search = find(instance.getFunctionName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getFunctionId(), instance.getFunctionId())) {
                instance.setIsActive(true);
                super.update(instance);
                DiaryEntityManager.createLog("Edited function \"" + instance.getFunctionName() + "\"");
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Update function khong thanh cong: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Disable of an object
     *
     * @param instance
     * @return  True:successfully setted / False: set failed.
     */
    @Override
    public boolean delete(Functions instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted action \"" + instance.getFunctionName() + "\"");
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
