package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.FawnPet;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Stateless
public class FawnPetSessionBean {

    @PersistenceContext(unitName = "1773031-1873004-UASPU")
    private EntityManager em;

    public List<FawnPet> getAllData() {
        Query q = em.createNamedQuery("FawnPet.findAll");
        return (List<FawnPet>) q.getResultList();
    }

    public List<FawnPet> getPetDataUser() {
        Query q = em.createQuery("SELECT f FROM FawnPet f WHERE f.iDuser = :usr");
        q.setParameter("usr", FawnUserSessionBean.loggedUser);
        List<FawnPet> val = (List<FawnPet>) q.getResultList();
        return val;
    }

    public List<FawnPet> getPetDataIsNotUser() {
        Query q = em.createQuery("SELECT f FROM FawnPet f WHERE NOT f.iDuser = :usr");
        q.setParameter("usr", FawnUserSessionBean.loggedUser);
        List<FawnPet> val = (List<FawnPet>) q.getResultList();
        return val;
    }

    public List<FawnPet> getPetDataFiltered(String[] args) {
        String base = "SELECT * FROM fawnder.fawn_pet f WHERE NOT f.ID_user = '" + String.valueOf(FawnUserSessionBean.loggedUser.getId())
                + "' AND f.visibility = '1'", attachment = "";
        for (String q : args) {
            if (q.split(",")[0].equals("location")) {
                attachment += " AND f.ID_user = ANY (SELECT u.ID FROM fawnder.fawn_user u WHERE u.location = '" + q.split(",")[1] + "')";
            } else {
                attachment += " AND f." + q.split(",")[0] + " = '" + q.split(",")[1] + "'";
            }
        }
        Query q = em.createNativeQuery(base + attachment, FawnPet.class);
        List<FawnPet> val = (List<FawnPet>) q.getResultList();
        return val;
    }

    public boolean insert(FawnPet fawnPet) {
        try {
            em.persist(fawnPet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(FawnPet fawnPet) {
        try {
            em.merge(fawnPet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(FawnPet fawnPet) {
        try {
            Query q = em.createNamedQuery("FawnPet.findById");
            q.setParameter("id", fawnPet.getId());
            FawnPet val = (FawnPet) q.getResultList().get(0);
            em.remove(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
