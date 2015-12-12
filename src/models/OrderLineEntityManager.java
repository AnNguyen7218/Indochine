/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.BookedRooms;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashley
 */
public class OrderLineEntityManager extends AbstractEntityManager<BookedRooms> {

    public OrderLineEntityManager() {
        super(BookedRooms.class);
    }

    
    /**
     * Get all object
     *
     * @return List<OrderLine> : list of BookedRooms object
     */
    public List<BookedRooms> getAllFromDB() {
        List<BookedRooms> list = super.getAll();
        return list;
    }

    /**
     * Get all active obj
     */
    public List<BookedRooms> getAllActive() {
        List<BookedRooms> list = new ArrayList<BookedRooms>();
        for(BookedRooms b:getAllFromDB()){
            if(b.getIsActive()==true) list.add(b);
        }
        return list;
    }

    /*Get obj by bill id*/
    public List<BookedRooms> getByBillID(int billID) {
        ArrayList<BookedRooms> list = new ArrayList<>();
        for(BookedRooms a : getAllFromDB()){
            if(a.getBookingRoom().getId() == billID){
                list.add(a);
            }
        }
        return list;
    }
    /**
     * get by room id
     */
    public BookedRooms getByRoomID(int roomID){
        
        for(BookedRooms b : getAllActive()){
            if(b.getRooms().getRoomId().equals(roomID)) return b;
        }
        return null;
    }
    
     @Override
    public boolean delete(BookedRooms instance) {
        try {

            if (super.delete(instance)) {
                DiaryEntityManager.createLog("Deleted booked room \"" + String.valueOf(instance.getId()) + "\"");
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
