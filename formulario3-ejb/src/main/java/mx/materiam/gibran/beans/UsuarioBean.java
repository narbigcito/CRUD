/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.materiam.gibran.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.materiam.gibran.entities.User;

/**
 *
 * @author gibran
 */
@Stateless
public class UsuarioBean implements UsuarioBeanLocal {

    @PersistenceContext(unitName = "mx.materiam.gibran_formulario3-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(UsuarioBean.class.getName());

    @Override
    public boolean createUser(User current) {
        try {
            em.persist(current);
            return true;
        } catch (Exception e) {
            LOG.severe("No se pudo persisitir");
            return false;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<User> getUsers() {
        try {
            Query q = em.createQuery("SELECT u FROM User u");
            return q.getResultList();
        } catch (Exception e) {
            LOG.severe("No se pudo traer todos los Usuarios");
            return null;
        }
    }

    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void delete(User current) {
        em.remove(current);
    }

   
}
