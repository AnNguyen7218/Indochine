/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Products;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class ProductEntityManager extends AbstractEntityManager<Products> {

    public ProductEntityManager() {
        super(Products.class);
    }

    /**
     * Get all object in active status (isActive=true)
     *
     * @return List<Product> : list of Products object
     */
    @Override
    public List<Products> getAll() {
        List<Products> list = super.getAll();

        List<Products> res = new ArrayList<>();

        for (Products ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    
    /**
     * Get all object
     *
     * @return List<Product> : list of Products object
     */
    public List<Products> getAllFromDB() {
        List<Products> list = super.getAll();
        return list;
    }

    /**
     * Find object by name
     *
     * @param name
     * @return found Products object
     */
    public Products find(String name) {
        for (Products ins : getAll()) {
            if (ins.getProductName().equalsIgnoreCase(name)) {
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
    public boolean addNew(Products instance) {
        if (find(instance.getProductName()) != null) {
            return false;
        }

        instance.setIsActive(true);

        if(super.insert(instance)){
        DiaryEntityManager.createLog("Created product \"" + instance.getProductName()+ "\"");
        return true;}
        else {System.out.println("Failed add pro");return false;}
    }

    /**
     * Update an object, with check exists name 
     * ID of updated object should be
     * exist in database, if not, it will create new one.
     *
     * @param instance
     * @param name
     */
    public boolean edit(Products instance) {
        try {
            Products search = find(instance.getProductName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getProductId(), instance.getProductId())) {
                instance.setIsActive(true);
                super.update(instance);
                DiaryEntityManager.createLog("Edited product \"" + instance.getProductName()+ "\"");
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Update product khong thanh cong: " + ex.getMessage());
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
    public boolean delete(Products instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted product \"" + instance.getProductName()+ "\"");
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
