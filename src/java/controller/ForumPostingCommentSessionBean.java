package controller;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.FawnUser;
import model.ForumPosting;
import model.ForumPostingComment;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Stateless
public class ForumPostingCommentSessionBean {

    @PersistenceContext(unitName = "1773031-1873004-UASPU")
    private EntityManager em;

    public List<ForumPostingComment> getAllCommentNotification(FawnUser loggedUser) {
        Query q = em.createNativeQuery("SELECT * FROM fawnder.forum_posting_comment f WHERE NOT f.ID_user = '" + loggedUser.getId() + "' "
                + "AND f.ID_posting = ANY (SELECT p.ID FROM fawnder.forum_posting p WHERE p.ID_user = '" + loggedUser.getId() + "')", ForumPostingComment.class);
        List<ForumPostingComment> output = (List<ForumPostingComment>) q.getResultList();
        Collections.reverse(output);
        return output;
    }

    public List<ForumPostingComment> getAllData() {
        Query q = em.createNamedQuery("ForumPostingComment.findAll");
        return (List<ForumPostingComment>) q.getResultList();
    }

    public List<ForumPostingComment> getAllPostingCommentByPosting(ForumPosting forumPosting) {
        Query q = em.createQuery("SELECT f FROM ForumPostingComment f WHERE f.iDposting = :iDposting");
        q.setParameter("iDposting", forumPosting);
        List<ForumPostingComment> output = (List<ForumPostingComment>) q.getResultList();
        Collections.reverse(output);
        return output;
    }

    public Integer getAllPostingCommentLengthByPosting(ForumPosting forumPosting) {
        Query q = em.createQuery("SELECT f FROM ForumPostingComment f WHERE f.iDposting = :iDposting");
        q.setParameter("iDposting", forumPosting);
        return ((List<ForumPostingComment>) q.getResultList()).size();
    }

    public Integer getAllUserPostingCommentLength(FawnUser fawnUser) {
        Query q = em.createQuery("SELECT f FROM ForumPostingComment f WHERE f.iDuser = :idUser");
        q.setParameter("idUser", fawnUser);
        return ((List<ForumPostingComment>) q.getResultList()).size();
    }

    public boolean insert(ForumPostingComment forumPostingComment) {
        try {
            em.persist(forumPostingComment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(ForumPostingComment forumPostingComment) {
        try {
            em.merge(forumPostingComment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(ForumPostingComment forumPostingComment) {
        try {
            Query q = em.createNamedQuery("ForumPostingComment.findById");
            q.setParameter("id", forumPostingComment.getId());
            ForumPostingComment val = (ForumPostingComment) q.getResultList().get(0);
            em.remove(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
