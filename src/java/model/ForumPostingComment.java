package model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Entity
@Table(name = "forum_posting_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ForumPostingComment.findAll", query = "SELECT f FROM ForumPostingComment f")
    , @NamedQuery(name = "ForumPostingComment.findById", query = "SELECT f FROM ForumPostingComment f WHERE f.id = :id")
    , @NamedQuery(name = "ForumPostingComment.findByDate", query = "SELECT f FROM ForumPostingComment f WHERE f.date = :date")
    , @NamedQuery(name = "ForumPostingComment.findByContent", query = "SELECT f FROM ForumPostingComment f WHERE f.content = :content")
    , @NamedQuery(name = "ForumPostingComment.findByHasEdit", query = "SELECT f FROM ForumPostingComment f WHERE f.hasEdit = :hasEdit")
    , @NamedQuery(name = "ForumPostingComment.findByEditDate", query = "SELECT f FROM ForumPostingComment f WHERE f.editDate = :editDate")})
public class ForumPostingComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 2500)
    @Column(name = "content")
    private String content;
    @Column(name = "hasEdit")
    private Short hasEdit;
    @Column(name = "editDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;
    @JoinColumn(name = "ID_posting", referencedColumnName = "ID")
    @ManyToOne
    private ForumPosting iDposting;
    @JoinColumn(name = "ID_user", referencedColumnName = "ID")
    @ManyToOne
    private FawnUser iDuser;

    public ForumPostingComment() {
    }

    public ForumPostingComment(Integer id) {
        this.id = id;
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

    public ForumPosting getIDposting() {
        return iDposting;
    }

    public void setIDposting(ForumPosting iDposting) {
        this.iDposting = iDposting;
    }

    public FawnUser getIDuser() {
        return iDuser;
    }

    public void setIDuser(FawnUser iDuser) {
        this.iDuser = iDuser;
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
        if (!(object instanceof ForumPostingComment)) {
            return false;
        }
        ForumPostingComment other = (ForumPostingComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ForumPostingComment[ id=" + id + " ]";
    }

}
