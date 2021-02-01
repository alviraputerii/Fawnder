package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.FawnUser;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Stateless
public class FawnUserSessionBean {

    @PersistenceContext(unitName = "1773031-1873004-UASPU")
    private EntityManager em;
    public static FawnUser loggedUser;

    public List<FawnUser> getAllData() {
        Query q = em.createNamedQuery("FawnUser.findAll");
        return (List<FawnUser>) q.getResultList();
    }

    public boolean insert(FawnUser fawnUser) {
        try {
            em.persist(fawnUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(FawnUser fawnUser) {
        try {
            em.merge(fawnUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(FawnUser fawnUser) {
        try {
            Query q = em.createNamedQuery("FawnUser.findAll");
            q.setParameter("id", fawnUser.getId());
            FawnUser val = (FawnUser) q.getResultList().get(0);
            em.remove(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkUsernameAlreadyExist(String username) {
        Query q = em.createQuery("SELECT f FROM FawnUser f WHERE f.loginUsername = :loginUsername");
        q.setParameter("loginUsername", username);
        return !q.getResultList().isEmpty();
    }

    public boolean login(String loginUsername, String loginPassword) {
        try {
            Query q = em.createQuery("SELECT f FROM FawnUser f WHERE f.loginUsername = :loginUsername AND f.loginPassword = :loginPassword");
            q.setParameter("loginUsername", loginUsername);
            q.setParameter("loginPassword", loginPassword);
            FawnUser val = (FawnUser) q.getSingleResult();
            loggedUser = val;
            return true;
        } catch (Exception e) {
            loggedUser = null;
            return false;
        }
    }

    public void logout() {
        loggedUser = null;
    }

    public FawnUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(FawnUser loggedUser) {
        FawnUserSessionBean.loggedUser = loggedUser;
    }
}
