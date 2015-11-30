/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Diary;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ashley
 */
public class DiaryEntityManager extends AbstractEntityManager<Diary> {

    public DiaryEntityManager() {
        super(Diary.class);
    }

    /**
     * Create a log for an action
     *
     * @param description description of the action
     * @return true if succeeded, else false.
     */
    public static boolean createLog(String description) {
        try {
            if (EmployeeEntityManager.currentEmployee == null) {
                return false;
            }

            DiaryEntityManager dm = new DiaryEntityManager();
            Diary d = new Diary();
            d.setAccounts(EmployeeEntityManager.currentEmployee);
            d.setDescription(description);
            d.setDateTime(new Date());
            if (dm.addNew(d)) {
                return true;
            }
        } catch (Exception ex) {
            System.err.println("Failed attempt to add diary: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Get all logs in diary database
     *
     * @return log list
     */
    @Override
    public List<Diary> getAll() {
        List<Diary> list = super.getAll();
        return list;
    }

    /**
     * Find a log by its ID
     *
     * @param id
     * @return found instance
     */
    public Diary find(int id) {
        for (Diary ins : getAll()) {
            if (ins.getDiaryId()== id) {
                return ins;
            }
        }
        System.out.println("Diary \"" + id + "\" doesn't exist.");
        return null;
    }

    /**
     * Add a new log
     *
     * @param instance
     * @return true if succeeded, else return false.
     */
    public boolean addNew(Diary instance) {

        if (super.insert(instance)) {
            return true;
        } else {
            return false;
        }
    }

//    /**
//     * Update new 
//     *
//     * @param instance ID cua instance phai ton tai trong CSDL, neu ko se tao
//     * mot ban ghi moi
//     * @param name
//     */
//    public boolean edit(Diary instance) {
//        Diary search = find(instance.getId());
//        
//        if (search == null || Objects.equals(search.getId(), instance.getId())) {
//            super.update(instance);
//            return true;
//        }
//        
//        return false;
//        
//    }
//    /**
//     * Xoa mot doi tuong (isActive=false)
//     *
//     * @param instance
//     */
//    @Override
//    public void delete(Diary instance) {
//        try {
//            super.update(instance);
//            
//        } catch (Exception ex) {
//            System.out.println("Xoa diary khong thanh cong: " + ex.getMessage());
//        }
//    }
    public List<Diary> getAllFromDB() {
        List<Diary> list = super.getAll();

        return list;
    }

    public static void main(String args[]) {
        DiaryEntityManager a = new DiaryEntityManager();
        Diary b = new Diary();
        b.setDescription("bla bla");
        a.addNew(b);
    }
}
