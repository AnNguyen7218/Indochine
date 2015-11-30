/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.OrderedDishes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashley
 */
public class OrderDishEntityManager extends AbstractEntityManager<OrderedDishes> {

    public OrderDishEntityManager() {
        super(OrderedDishes.class);
    }

    /**
     * Get all object
     *
     * @return List<OrderLine> : list of OrderedDishes object
     */
    public List<OrderedDishes> getAllFromDB() {
        List<OrderedDishes> list = super.getAll();
        return list;
    }

    public List<OrderedDishes> getByBillID(int billID) {
        ArrayList<OrderedDishes> list = new ArrayList<>();
        for (OrderedDishes a : getAllFromDB()) {
            if (a.getRestaurantBill().getId() == billID) {
                list.add(a);
            }
        }
        return list;
    }

}
