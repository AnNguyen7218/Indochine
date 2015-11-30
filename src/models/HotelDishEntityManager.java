/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.HotelOrderDish;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ashley
 */
public class HotelDishEntityManager extends AbstractEntityManager<HotelOrderDish> {

    public HotelDishEntityManager() {
        super(HotelOrderDish.class);
    }

    /**
     * Get all object
     *
     * @return List<OrderLine> : list of HotelOrderDish object
     */
    public List<HotelOrderDish> getAllFromDB() {
        List<HotelOrderDish> list = super.getAll();
        return list;
    }

    public List<HotelOrderDish> getByRoomId(int RoomId) {
        ArrayList<HotelOrderDish> list = new ArrayList<>();
        for (HotelOrderDish a : getAllFromDB()) {
            if (a.getRooms().getRoomId() == RoomId) {
                list.add(a);
            }
        }
        return list;
    }

    /**
     * Add new object which check validity
     *
     * @param instance
     * @return True:successfully added / False: add failed.
     */
    public boolean addNew(HotelOrderDish instance) {

        if (super.insert(instance)) {
            DiaryEntityManager.createLog("Created product \"" + instance.getId() + "\"");
            return true;
        } else {
            System.out.println("Failed add order dish from hotel");
            return false;
        }
    }

    public boolean edit(HotelOrderDish instance) {

        HotelOrderDish search = find(instance.getId());

        if (search == null || Objects.equals(search.getId(), instance.getId())) {
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Edited HOTEL ORDER SER #" + instance.getId());
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    @Override
    public boolean delete(HotelOrderDish instance) {
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
