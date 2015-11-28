/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;

/**
 *
 * @author bo
 */
public class AdminFacade {

//    private static EntityManagerFactory emf;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    private AdminFacade() {

//        AdminFacade.emf = DBConnector.getInstance().getEmFactory();
    }

    // Singleton -------------------------------------------------
    public static AdminFacade getInstance() {
        return AdminFacadeHolder.INSTANCE;
    }

    private static class AdminFacadeHolder {

        private static final AdminFacade INSTANCE = new AdminFacade();
    }
    // ---------------------------------------------------------

    public entity.User deleteUser(String uName) {

        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, uName);

        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            return user;

        } finally {
            em.close();
        }

    } // End of delete user

    // Get all users
    public List<entity.User> getAllUsers() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT c FROM User c");

        List<entity.User> results = null;
        try {
            results = query.getResultList();
        } catch (Exception e) {
            System.out.println("Fejl i getAllPersons: " + e.getMessage());
        }
        return results;

    } // End of method

} // End of class
