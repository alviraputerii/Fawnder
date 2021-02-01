package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.FawnPetNotification;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Stateless
public class FawnPetNotificationSessionBean {

    @PersistenceContext(unitName = "1773031-1873004-UASPU")
    private EntityManager em;

    public List<FawnPetNotification> getAllData() {
        Query q = em.createNamedQuery("FawnPetNotification.findAll");
        return (List<FawnPetNotification>) q.getResultList();
    }

    public boolean insert(FawnPetNotification fawnPetNotification) {
        try {
            em.persist(fawnPetNotification);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(FawnPetNotification fawnPetNotification) {
        try {
            em.merge(fawnPetNotification);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(FawnPetNotification fawnPetNotification) {
        try {
            Query q = em.createNamedQuery("FawnPetNotification.findById");
            q.setParameter("id", fawnPetNotification.getId());
            FawnPetNotification val = (FawnPetNotification) q.getResultList().get(0);
            em.remove(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<FawnPetNotification> getSentNotification() {
        Query q = em.createQuery("SELECT f FROM FawnPetNotification f WHERE f.iDpetSender.iDuser = :sender");
        q.setParameter("sender", FawnUserSessionBean.loggedUser);
        List<FawnPetNotification> val = (List<FawnPetNotification>) q.getResultList();
        return val;
    }

    public List<FawnPetNotification> getReceiveNotification() {
        Query q = em.createQuery("SELECT f FROM FawnPetNotification f WHERE f.iDpetReceiver.iDuser = :receiver");
        q.setParameter("receiver", FawnUserSessionBean.loggedUser);
        List<FawnPetNotification> val = (List<FawnPetNotification>) q.getResultList();
        return val;
    }
}
