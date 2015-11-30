/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Suppliers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class SupplierEntityManager extends AbstractEntityManager<Suppliers> {

    public SupplierEntityManager() {
        super(Suppliers.class);
    }

    /**
     * Get all active categories
     *
     * @return the list
     */
    @Override
    public List<Suppliers> getAll() {
        List<Suppliers> list = super.getAll();

        List<Suppliers> res = new ArrayList<>();

        for (Suppliers ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    /**
     * Get all object
     *
     * @return List : list of Suppliers object
     */
    public List<Suppliers> getAllFromDB() {
        List<Suppliers> list = super.getAll();
        return list;
    }

    /**
     * Find category by its name
     *
     * @param name
     * @return found instance
     */
    public Suppliers find(String name) {
        for (Suppliers ins : getAll()) {
            if (ins.getSupplierName().equalsIgnoreCase(name)) {
                return ins;
            }
        }

        System.err.println("Category \"" + name + "\" doesn't exists");
        return null;
    }

    /**
     * Add new category
     *
     * @param instance
     * @return true if succeeded, else false
     */
    public boolean addNew(Suppliers instance) {
        if (instance.getSupplierName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getSupplierName()) != null) {
                System.err.println("Category already exists!");
                return false;
            }

            super.insert(instance);
            DiaryEntityManager.createLog("Created category \"" + instance.getSupplierName() + "\"");
            return true;
        } catch (Exception ex) {
            System.out.println("Failed to add new category: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Update category's information. If the category doesn't exist, create a
     * new one.
     *
     * @param instance new information
     * @return
     */
    public boolean edit(Suppliers instance) {
        if (instance.getSupplierName().isEmpty()) {
            return false;
        }

        try {
            Suppliers search = find(instance.getSupplierName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getSupplierId(), instance.getSupplierId())) {
                super.update(instance);
                DiaryEntityManager.createLog("Edited category \"" + instance.getSupplierName() + "\"");
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Failed to update category: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Disable a category
     *
     * @param instance
     */
    @Override
    public boolean delete(Suppliers instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted category \"" + instance.getSupplierName() + "\"");
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
