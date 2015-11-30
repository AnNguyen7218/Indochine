/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Rooms;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class RoomEntityManager extends AbstractEntityManager<Rooms> {

    public RoomEntityManager() {
        super(Rooms.class);
    }

    /**
     * Get all object in active status (isActive=true)
     *
     * @return List<Product> : list of Rooms object
     */
    @Override
    public List<Rooms> getAll() {
        List<Rooms> list = super.getAll();

        List<Rooms> res = new ArrayList<>();

        for (Rooms ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    
    /**
     * Get all object
     *
     * @return List<Product> : list of Rooms object
     */
    public List<Rooms> getAllFromDB() {
        List<Rooms> list = super.getAll();
        return list;
    }

    public List<Rooms> getAllBookedRoom() {
        List<Rooms> list = super.getAll();
       List<Rooms> res = new ArrayList<>();

        for (Rooms ins : list) {
            if (ins.getStatus().equals(3)) {
                res.add(ins);
            }
        }
        return res;
    }
    
    /**
     * Find object by name
     *
     * @param name
     * @return found Rooms object
     */
    public Rooms find(String name) {
        for (Rooms ins : getAll()) {
            if (ins.getRoomName().equalsIgnoreCase(name)) {
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
    public boolean addNew(Rooms instance) {
        if (find(instance.getRoomName()) != null) {
            return false;
        }

        instance.setIsActive(true);

        if(super.insert(instance)){
        DiaryEntityManager.createLog("Created product \"" + instance.getRoomName()+ "\"");
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
    public boolean edit(Rooms instance) {
        try {
            Rooms search = find(instance.getRoomName());
            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getRoomId(), instance.getRoomId())) {
                instance.setIsActive(true);
                super.update(instance);
                DiaryEntityManager.createLog("Edited product \"" + instance.getRoomName()+ "\"");
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
    public boolean delete(Rooms instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted product \"" + instance.getRoomName()+ "\"");
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
