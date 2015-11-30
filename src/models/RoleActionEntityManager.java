/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.RoleFunction;
import java.util.List;

/**
 *
 * @author lehai
 */
public class RoleActionEntityManager extends AbstractEntityManager<RoleFunction>{

    public RoleActionEntityManager() {
        super(RoleFunction.class);
    }
    
    public List<RoleFunction> getAllFromDB(){
        List<RoleFunction> list = super.getAll();
        return list;
    }
    /**
     * Find relation between role and function
     * @param roleID
     * @param functionID
     * @return 
     */
    public RoleFunction find(int roleID,int functionID){
        for(RoleFunction rf:super.getAll()){
            if(rf.getFunctions().getFunctionId()==functionID&&rf.getRoles().getRoleId()==roleID){
                return rf;
            }
        }
        return null;
    }
    
}
