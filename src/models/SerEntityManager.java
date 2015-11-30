/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import entities.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class SerEntityManager extends AbstractEntityManager<Services> {

    public SerEntityManager() {
        super(Services.class);
    }

    /**
     * Get all object in active status (isActive=true)
     *
     * @return List<Product> : list of Services object
     */
    @Override
    public List<Services> getAll() {
        List<Services> list = super.getAll();

        List<Services> res = new ArrayList<>();

        for (Services ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    
    /**
     * Get all object
     *
     * @return List<Product> : list of Services object
     */
    public List<Services> getAllFromDB() {
        List<Services> list = super.getAll();
        return list;
    }

    /**
     * Find object by name
     *
     * @param name
     * @return found Services object
     */
    public Services find(String name) {
        for (Services ins : getAll()) {
            if (ins.getServiceName().equalsIgnoreCase(name)) {
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
    public boolean addNew(Services instance) {
        if (find(instance.getServiceName()) != null) {
            return false;
        }

        instance.setIsActive(true);

        if(super.insert(instance)){
        DiaryEntityManager.createLog("Created product \"" + instance.getServiceName()+ "\"");
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
    public boolean edit(Services instance) {
        try {
            Services search = find(instance.getServiceName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getServiceId(), instance.getServiceId())) {
                instance.setIsActive(true);
                super.update(instance);
                DiaryEntityManager.createLog("Edited product \"" + instance.getServiceName()+ "\"");
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
    public boolean delete(Services instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted product \"" + instance.getServiceName()+ "\"");
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
