package controller;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.FawnUser;
import model.ForumPosting;
import model.ForumTopic;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Stateless
public class ForumPostingSessionBean {

    @PersistenceContext(unitName = "1773031-1873004-UASPU")
    private EntityManager em;

    public List<ForumPosting> getAllDataFiltered(ForumTopic forumTopic) {
        if (forumTopic == null) {
            return getAllData();
        }
        Query q = em.createQuery("SELECT f FROM ForumPosting f WHERE f.iDtopic = :idTopic");
        q.setParameter("idTopic", forumTopic);
        List<ForumPosting> output = (List<ForumPosting>) q.getResultList();
        Collections.reverse(output);
        return output;
    }

    public List<ForumPosting> getAllDataByUser(FawnUser fawnUser) {
        Query q = em.createQuery("SELECT f FROM ForumPosting f WHERE f.iDuser = :idUser");
        q.setParameter("idUser", fawnUser);
        List<ForumPosting> output = (List<ForumPosting>) q.getResultList();
        Collections.reverse(output);
        return output;
    }

    public Integer getAllUserPostingLength(FawnUser fawnUser) {
        Query q = em.createQuery("SELECT f FROM ForumPosting f WHERE f.iDuser = :idUser");
        q.setParameter("idUser", fawnUser);
        return ((List<ForumPosting>) q.getResultList()).size();
    }

    public List<ForumPosting> getAllData() {
        Query q = em.createNamedQuery("ForumPosting.findAll");
        List<ForumPosting> output = (List<ForumPosting>) q.getResultList();
        Collections.reverse(output);
        return output;
    }

    public boolean insert(ForumPosting forumTopic) {
        try {
            em.persist(forumTopic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(ForumPosting forumTopic) {
        try {
            em.merge(forumTopic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(ForumPosting forumTopic) {
        try {
            Query q = em.createNamedQuery("ForumPosting.findById");
            q.setParameter("id", forumTopic.getId());
            ForumPosting val = (ForumPosting) q.getResultList().get(0);
            em.remove(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
