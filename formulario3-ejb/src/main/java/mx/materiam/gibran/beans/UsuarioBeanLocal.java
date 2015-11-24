/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.materiam.gibran.beans;

import java.util.List;
import javax.ejb.Local;
import mx.materiam.gibran.entities.User;

/**
 *
 * @author gibran
 */
@Local
public interface UsuarioBeanLocal {
    
    boolean createUser(String nombre, String password);
    List<User> getUsers();
    User getUser(long id);
}
