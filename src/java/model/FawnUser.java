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
@Table(name = "fawn_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FawnUser.findAll", query = "SELECT f FROM FawnUser f")
    , @NamedQuery(name = "FawnUser.findById", query = "SELECT f FROM FawnUser f WHERE f.id = :id")
    , @NamedQuery(name = "FawnUser.findByLoginUsername", query = "SELECT f FROM FawnUser f WHERE f.loginUsername = :loginUsername")
    , @NamedQuery(name = "FawnUser.findByLoginPassword", query = "SELECT f FROM FawnUser f WHERE f.loginPassword = :loginPassword")
    , @NamedQuery(name = "FawnUser.findByName", query = "SELECT f FROM FawnUser f WHERE f.name = :name")
    , @NamedQuery(name = "FawnUser.findByContact", query = "SELECT f FROM FawnUser f WHERE f.contact = :contact")
    , @NamedQuery(name = "FawnUser.findByLocation", query = "SELECT f FROM FawnUser f WHERE f.location = :location")})
public class FawnUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "loginUsername")
    private String loginUsername;
    @Size(max = 12)
    @Column(name = "loginPassword")
    private String loginPassword;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 75)
    @Column(name = "contact")
    private String contact;
    @Size(max = 250)
    @Column(name = "location")
    private String location;
    @OneToMany(mappedBy = "iDuser")
    private Collection<ForumPosting> forumPostingCollection;
    @OneToMany(mappedBy = "iDuser")
    private Collection<FawnPet> fawnPetCollection;
    @OneToMany(mappedBy = "iDuser")
    private Collection<ForumPostingComment> forumPostingCommentCollection;

    public FawnUser() {
    }

    public FawnUser(FawnUser u) {
        id = u.getId();
        loginUsername = u.getLoginUsername();
        loginPassword = u.getLoginPassword();
        name = u.getName();
        contact = u.getContact();
        location = u.getLocation();
    }

    public FawnUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<ForumPosting> getForumPostingCollection() {
        return forumPostingCollection;
    }

    public void setForumPostingCollection(Collection<ForumPosting> forumPostingCollection) {
        this.forumPostingCollection = forumPostingCollection;
    }

    @XmlTransient
    public Collection<FawnPet> getFawnPetCollection() {
        return fawnPetCollection;
    }

    public void setFawnPetCollection(Collection<FawnPet> fawnPetCollection) {
        this.fawnPetCollection = fawnPetCollection;
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
        if (!(object instanceof FawnUser)) {
            return false;
        }
        FawnUser other = (FawnUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FawnUser[ id=" + id + " ]";
    }

}
