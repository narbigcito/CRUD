/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.materiam.gibran;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import mx.materiam.gibran.entities.User;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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

    /**
     * Creates a new instance of ManagerBean
     */
    public ManagerBean() {
        current = new User();
    }

    public List<User> getUsers() {
        return usuarioBean.getUsers();
    }

    public User getDetails() {
        return usuarioBean.getUser(1);
    }

    public boolean createUser() {

        System.out.println("El archivo se ha creado correctamente");
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

    public void delete() {
        usuarioBean.delete(current);
    }

    public void update() {
        usuarioBean.update(current);
    }

    public void upload(FileUploadEvent evt) {
        UploadedFile uf = evt.getFile();
        if (uf != null) {
            try {
                byte[] bytes = IOUtils.toByteArray(uf.getInputstream());
                current.setArrayPdf(bytes);
            } catch (IOException ex) {
                Logger.getLogger(ManagerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void dowload() {
        InputStream myInputStream = new ByteArrayInputStream(usuarioBean.dowload(current));

       
    }

}
