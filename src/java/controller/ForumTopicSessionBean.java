package controller;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.ForumTopic;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Stateless
public class ForumTopicSessionBean {

    @PersistenceContext(unitName = "1773031-1873004-UASPU")
    private EntityManager em;

    public List<ForumTopic> getAllData() {
        Query q = em.createNamedQuery("ForumTopic.findAll");
        List<ForumTopic> output = (List<ForumTopic>) q.getResultList();
        Collections.reverse(output);
        return output;
    }

    public boolean insert(ForumTopic forumTopic) {
        try {
            em.persist(forumTopic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(ForumTopic forumTopic) {
        try {
            em.merge(forumTopic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(ForumTopic forumTopic) {
        try {
            Query q = em.createNamedQuery("ForumTopic.findAll");
            q.setParameter("id", forumTopic.getId());
            ForumTopic val = (ForumTopic) q.getResultList().get(0);
            em.remove(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
