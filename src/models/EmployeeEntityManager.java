/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Accounts;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utils.MD5Encryption;

/**
 *
 * @author lehai
 */
/**
 * Accounts data entity manager
 *
 * @author lehai
 */
public class EmployeeEntityManager extends AbstractEntityManager<Accounts> {

    public EmployeeEntityManager() {
        super(Accounts.class);
    }

    /**
     * Current logged in account
     */
    public static Accounts currentEmployee = null;

    /**
     * Get all of the employees account which are still active
     *
     * @return list of active accounts
     */
    @Override
    public List<Accounts> getAll() {
        List<Accounts> list = super.getAll();
        List<Accounts> res = new ArrayList<>();
        //Filter out those accounts that are not active
        for (Accounts ins : list) {
            if (ins.getIsActive()) {
                res.add(ins);
            }
        }
        return res;
    }

   

    /**
     * Get all of employee accounts from the database
     *
     * @return list of all employee accounts
     */
    public List<Accounts> getAccFromDB() {
        List<Accounts> list = super.getAll();
        return list;
    }

    /**
     * Find account by username
     *
     * @param username
     * @return Accounts object if found, else return null
     *
     */
    public Accounts find(String username) {
        for (Accounts i : getAll()) {
            if (i.getAccountName().equalsIgnoreCase(username)) {
                return i;
            }
        }

        System.err.println("Cannot find account " + username);
        return null;
    }

    /**
     * Add a new employee to the database
     *
     * @param instance
     * @return true if succeeded, else false
     */
    public boolean addNew(Accounts instance) {
        if (instance.getAccountName().isEmpty()) {
            return false;
        }
        try {
            if (find(instance.getAccountName()) != null) {
                System.err.println("Employee is already exists!");
                return false;
            }

            instance.setAccountPass(MD5Encryption.encryptWithMD5(instance.getAccountPass()));

            if (super.insert(instance)) {
                DiaryEntityManager.createLog("Added new employee \"" + instance.getAccountName()+ "\"");
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Failed attempt to add new instance: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Update employee's information
     *
     * @param instance new information
     * @return true if succeeded, else false
     *
     */
    public boolean edit(Accounts instance) {
        if (instance.getAccountName().isEmpty()) {
            return false;
        }

        try {
            Accounts search = find(instance.getAccountName());

            if (search == null || Objects.equals(search.getAccountId(), instance.getAccountId())) {

                //instance.setPassword(MD5Encryption.encryptWithMD5(instance.getPassword()));
                if (super.update(instance)) {
                    DiaryEntityManager.createLog("Updated employee \"" + instance.getAccountName()+ "\"");
                    return true;
                } else {
                    return false;
                }
            }

            return false;

        } catch (Exception ex) {
            System.out.println("Failed to update new information: " + ex.getMessage());
            return false;
        }
    }

    

    /**
     * Deactivate an account
     *
     * @param instance
     * @return
     */
    @Override
    public boolean delete(Accounts instance) {
        try {
            instance.setIsActive(false);
            if (super.update(instance)) {
                DiaryEntityManager.createLog("Deleted employee \"" + instance.getAccountName()+ "\"");
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
     * Login and update login session to the system
     *
     * @param username
     * @param password
     * @return true if succeeded, else false
     */
    public boolean login(String username, String password) {
        String encryptedPassword = MD5Encryption.encryptWithMD5(password);
        for (Accounts acc : getAll()) {
            if (username.equals(acc.getAccountName()) && encryptedPassword.equals(acc.getAccountPass())) {
                currentEmployee = acc;
                DiaryEntityManager.createLog("Logged in to the system.");
                return true;
            }
        }
        return false;
    }
}
