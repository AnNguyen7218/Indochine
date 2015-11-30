/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.CategoryOfRoom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class CateOfRoomEntityManager extends AbstractEntityManager<CategoryOfRoom> {

    public CateOfRoomEntityManager() {
        super(CategoryOfRoom.class);
    }

    /**
     * Get all active categories
     *
     * @return the list
     */
    @Override
    public List<CategoryOfRoom> getAll() {
        List<CategoryOfRoom> list = super.getAll();

        List<CategoryOfRoom> res = new ArrayList<>();

        for (CategoryOfRoom ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    
    /**
     * Get all object
     *
     * @return List : list of CategoryOfRoom object
     */
    public List<CategoryOfRoom> getAllFromDB() {
        List<CategoryOfRoom> list = super.getAll();
        return list;
    }

    /**
     * Find category by its name
     *
     * @param name
     * @return found instance
     */
    public CategoryOfRoom find(String name) {
        for (CategoryOfRoom ins : getAll()) {
            if (ins.getCateRoomName().equalsIgnoreCase(name)) {
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
    public boolean addNew(CategoryOfRoom instance) {
        if (instance.getCateRoomName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getCateRoomName()) != null) {
                System.err.println("Category already exists!");
                return false;
            }

            super.insert(instance);
            DiaryEntityManager.createLog("Created category \"" + instance.getCateRoomName() + "\"");
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
    public boolean edit(CategoryOfRoom instance) {
        if (instance.getCateRoomName().isEmpty()) {
            return false;
        }

        try {
            CategoryOfRoom search = find(instance.getCateRoomName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getId(), instance.getId())) {
                super.update(instance);
                DiaryEntityManager.createLog("Edited category \"" + instance.getCateRoomName() + "\"");
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
    public boolean delete(CategoryOfRoom instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted category \"" + instance.getCateRoomName() + "\"");
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
