/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Functions;
import entities.Roles;
import entities.RoleFunction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author lehai
 */
public class RoleEntityManager extends AbstractEntityManager<Roles> {

    public RoleEntityManager() {
        super(Roles.class);
    }

    /**
     * Get all object in active status (isActive=true)
     *
     * @return List<Role> : list of Roles object
     */
    @Override
    public List<Roles> getAll() {
        List<Roles> list = super.getAll();

        List<Roles> res = new ArrayList<>();

        for (Roles ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

    /**
     * Get all object
     *
     * @return List<Role> : list of Roles object
     */
    public List<Roles> getAllFromDB() {
        List<Roles> list = super.getAll();
        return list;
    }

    /**
     * Find object by name
     *
     * @param name
     * @return found Roles object
     */
    public Roles find(String name) {
        for (Roles ins : getAll()) {
            if (ins.getRoleName().equalsIgnoreCase(name)) {
                return ins;
            }
        }
        return null;
    }

    /**
     * Add new object which check validity
     *
     * @param instance
     * @return True:successfully added / False: add failed.
     */
    public boolean addNew(Roles instance, Set<Functions> funcList) {
        if (instance.getRoleName().isEmpty()) {
            return false;
        }

        if (find(instance.getRoleName()) != null) {
            System.err.println("Role da ton tai!");
            return false;
        }

        try {
            instance.setIsActive(true);
            super.insert(instance);

            DiaryEntityManager.createLog("Created role \"" + instance.getRoleName() + "\"");

            RoleActionEntityManager rfModel = new RoleActionEntityManager();

            for (Functions func : funcList) {
                RoleFunction rf = new RoleFunction();
                rf.setRoles(instance);
                rf.setFunctions(func);
                rfModel.insert(rf);
                DiaryEntityManager.createLog("Grant \"" + func.getFunctionName()+ "\" permission to role \"" + instance.getRoleName() + "\"");
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    /**
     * Update an object, with check exists name 
     * ID of updated object should be
     * exist in database, if not, it will create new one.
     *
     * @param instance
     * @param name
     */
    public boolean edit(Roles instance, Set<Functions> funcList) {
        if (instance.getRoleName().isEmpty()) {
            return false;
        }

        Roles search = find(instance.getRoleName());

        try {

            //Neu doi ten thi phai bao dam la ten nay chua ton tai
            if (search == null || Objects.equals(search.getRoleId(), instance.getRoleId())) {
                instance.setIsActive(true);
                super.update(instance);

                RoleActionEntityManager rfModel = new RoleActionEntityManager();

                //Xoa danh sach function cu
                for (RoleFunction rf : rfModel.getAll()) {
                    if (rf.getRoles().getRoleId() == instance.getRoleId()) {
                        rfModel.delete(rf);
                    }
                }
                //Add lai danh sach moi
                for (Functions func : funcList) {
                    RoleFunction rf = new RoleFunction();
                    rf.setRoles(instance);
                    rf.setFunctions(func);
                    rfModel.insert(rf);
                }
                DiaryEntityManager.createLog("Edited role \"" + instance.getRoleName() + "\"");
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.err.println("Update role khong thanh cong: " + ex.getMessage());
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
    public boolean delete(Roles instance) {
        if (instance == null) {
            return false;
        }

        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted role \"" + instance.getRoleName() + "\"");
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Failed to disable instance: " + ex.getMessage());
            return false;
        }

    }

    /**
     * List function of role
     *
     * @param instance
     * @return list function
     */
    public Set<Functions> getFunctionList(Roles instance) {
        if (instance == null) {
            return null;
        }

        Set<Functions> res = new HashSet<>();
        RoleActionEntityManager rfModel = new RoleActionEntityManager();

        for (RoleFunction rf : rfModel.getAll()) {
            if (Objects.equals(rf.getRoles().getRoleId(), instance.getRoleId())) {
                res.add(rf.getFunctions());
            }
        }

        return res;

    }
}
