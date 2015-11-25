/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.materiam.gibran;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import mx.materiam.gibran.entities.User;

/**
 *
 * @author gibran
 */
@Named(value = "managerBean")
@SessionScoped
public class ManagerBean implements Serializable {
    @EJB
    private mx.materiam.gibran.beans.UsuarioBeanLocal usuarioBean;
    private User current;
    private User selectUser;

    /**
     * Creates a new instance of ManagerBean
     */
    public ManagerBean() {
        current = new User();
    }

    
   public List<User> getUsers(){
       return  usuarioBean.getUsers();
   }
   
   public User getDetails(){
       return usuarioBean.getUser(1);
   }
   
    public  boolean createUser(){
        return usuarioBean.createUser(current);
       
    }

    /**
     * @return the current
     */
    public User getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(User current) {
        this.current = current;
    }

    public void delete(){
        usuarioBean.delete(current);
    } 

    /**
     * @return the selectUser
     */
    public User getSelectUser() {
        return selectUser;
    }

    /**
     * @param selectUser the selectUser to set
     */
    public void setSelectUser(User selectUser) {
        this.selectUser = selectUser;
    }
   
   
    
    
}


