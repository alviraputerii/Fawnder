package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Entity
@Table(name = "forum_posting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ForumPosting.findAll", query = "SELECT f FROM ForumPosting f")
    , @NamedQuery(name = "ForumPosting.findById", query = "SELECT f FROM ForumPosting f WHERE f.id = :id")
    , @NamedQuery(name = "ForumPosting.findByDate", query = "SELECT f FROM ForumPosting f WHERE f.date = :date")
    , @NamedQuery(name = "ForumPosting.findBySubject", query = "SELECT f FROM ForumPosting f WHERE f.subject = :subject")
    , @NamedQuery(name = "ForumPosting.findByContent", query = "SELECT f FROM ForumPosting f WHERE f.content = :content")
    , @NamedQuery(name = "ForumPosting.findByHasEdit", query = "SELECT f FROM ForumPosting f WHERE f.hasEdit = :hasEdit")
    , @NamedQuery(name = "ForumPosting.findByEditDate", query = "SELECT f FROM ForumPosting f WHERE f.editDate = :editDate")})
public class ForumPosting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 50)
    @Column(name = "subject")
    private String subject;
    @Size(max = 2500)
    @Column(name = "content")
    private String content;
    @Column(name = "hasEdit")
    private Short hasEdit;
    @Column(name = "editDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;
    @JoinColumn(name = "ID_topic", referencedColumnName = "ID")
    @ManyToOne
    private ForumTopic iDtopic;
    @JoinColumn(name = "ID_user", referencedColumnName = "ID")
    @ManyToOne
    private FawnUser iDuser;
    @OneToMany(mappedBy = "iDposting")
    private Collection<ForumPostingComment> forumPostingCommentCollection;

    public ForumPosting() {
    }

    public ForumPosting(Integer id) {
        this.id = id;
    }

    public ForumPosting(ForumPosting fp) {
        id = fp.getId();
        date = fp.getDate();
        subject = fp.subject;
        content = fp.content;
        hasEdit = fp.hasEdit;
        iDtopic = fp.iDtopic;
        iDuser = fp.iDuser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getHasEdit() {
        try {
            return hasEdit == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public void setHasEdit(boolean hasEdit) {
        this.hasEdit = (short) (hasEdit ? 1 : 0);
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public ForumTopic getIDtopic() {
        return iDtopic;
    }

    public void setIDtopic(ForumTopic iDtopic) {
        this.iDtopic = iDtopic;
    }

    public FawnUser getIDuser() {
        return iDuser;
    }

    public void setIDuser(FawnUser iDuser) {
        this.iDuser = iDuser;
    }

    @XmlTransient
    public Collection<ForumPostingComment> getForumPostingCommentCollection() {
        return forumPostingCommentCollection;
    }

    public void setForumPostingCommentCollection(Collection<ForumPostingComment> forumPostingCommentCollection) {
        this.forumPostingCommentCollection = forumPostingCommentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ForumPosting)) {
            return false;
        }
        ForumPosting other = (ForumPosting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ForumPosting[ id=" + id + " ]";
    }

}
