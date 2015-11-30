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

    public List<BookedRooms> getByBillID(int billID) {
        ArrayList<BookedRooms> list = new ArrayList<>();
        for(BookedRooms a : getAllFromDB()){
            if(a.getBookingRoom().getId() == billID){
                list.add(a);
            }
        }
        return list;
    }
}
