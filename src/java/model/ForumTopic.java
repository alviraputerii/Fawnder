package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Entity
@Table(name = "forum_topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ForumTopic.findAll", query = "SELECT f FROM ForumTopic f")
    , @NamedQuery(name = "ForumTopic.findById", query = "SELECT f FROM ForumTopic f WHERE f.id = :id")
    , @NamedQuery(name = "ForumTopic.findByName", query = "SELECT f FROM ForumTopic f WHERE f.name = :name")})
public class ForumTopic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "iDtopic")
    private Collection<ForumPosting> forumPostingCollection;

    public ForumTopic() {
    }

    public ForumTopic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<ForumPosting> getForumPostingCollection() {
        return forumPostingCollection;
    }

    public void setForumPostingCollection(Collection<ForumPosting> forumPostingCollection) {
        this.forumPostingCollection = forumPostingCollection;
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
        if (!(object instanceof ForumTopic)) {
            return false;
        }
        ForumTopic other = (ForumTopic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ForumTopic[ id=" + id + " ]";
    }

}
