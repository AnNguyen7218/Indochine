/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.BookedRooms;
import entities.BookingRoom;
import entities.Rooms;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Ashley
 */
public class OrderEntityManager extends AbstractEntityManager<BookingRoom> {

    public OrderEntityManager() {
        super(BookingRoom.class);
    }

    /**
     * Get all object in active status (isActive=true)
     *
     * @return List list of Order object is active
     */
    @Override
    public List<BookingRoom> getAll() {
        List<BookingRoom> list = super.getAll();

        return list;
    }

  

    /**
     * Search for a booking by roomid.
     *
     * @param name
     * @return the found customer
     */
    public BookingRoom find(Rooms room) {
        
        
        for (BookingRoom ins : getAll()) {
            Set<BookedRooms> set = ins.getBookedRoomses();
            for(BookedRooms r:set){
                if(r.getRooms().equals(room)){
                    return ins;
                }
            }
        }
        return null;
    }

    /**
     * Add new object
     *
     * @param instance
     * @return True:successfully added / False: add failed.
     */
    public boolean addNew(BookingRoom instance) {

        if (super.insert(instance)) {
            DiaryEntityManager.createLog("Created bill #" + instance.getId());
            return true;
        } else {
            return false;
        }

    }

    /**
     * Update an object, with check exists name ID of updated object should be
     * exist in database, if not, it will create new one.
     *
     * @param instance
     * @return 
     */
    public boolean edit(BookingRoom instance) {

        BookingRoom search = find(instance.getId());

        if (search == null || Objects.equals(search.getId(), instance.getId())) {
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Edited bill #" + instance.getId());
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

   
}
