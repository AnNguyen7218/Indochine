/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.CategoryOfProduct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class CateOfProEntityManager extends AbstractEntityManager<CategoryOfProduct>{
     public CateOfProEntityManager() {
        super(CategoryOfProduct.class);
    }

    /**
     * Get all active categories
     *
     * @return the list
     */
    @Override
    public List<CategoryOfProduct> getAll() {
        List<CategoryOfProduct> list = super.getAll();

        List<CategoryOfProduct> res = new ArrayList<>();

        for (CategoryOfProduct ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    
    /**
     * Get all object
     *
     * @return List : list of CategoryOfProduct object
     */
    public List<CategoryOfProduct> getAllFromDB() {
        List<CategoryOfProduct> list = super.getAll();
        return list;
    }

    /**
     * Find category by its name
     *
     * @param name
     * @return found instance
     */
    public CategoryOfProduct find(String name) {
        for (CategoryOfProduct ins : getAll()) {
            if (ins.getCateName().equalsIgnoreCase(name)) {
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
    public boolean addNew(CategoryOfProduct instance) {
        if (instance.getCateName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getCateName()) != null) {
                System.err.println("Category already exists!");
                return false;
            }

            super.insert(instance);
            DiaryEntityManager.createLog("Created category \"" + instance.getCateName() + "\"");
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
    public boolean edit(CategoryOfProduct instance) {
        if (instance.getCateName().isEmpty()) {
            return false;
        }

        try {
            CategoryOfProduct search = find(instance.getCateName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getId(), instance.getId())) {
                super.update(instance);
                DiaryEntityManager.createLog("Edited category \"" + instance.getCateName() + "\"");
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
    public boolean delete(CategoryOfProduct instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted category \"" + instance.getCateName() + "\"");
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
