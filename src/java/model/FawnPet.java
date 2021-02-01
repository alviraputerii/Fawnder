package model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Entity
@Table(name = "fawn_pet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FawnPet.findAll", query = "SELECT f FROM FawnPet f")
    , @NamedQuery(name = "FawnPet.findById", query = "SELECT f FROM FawnPet f WHERE f.id = :id")
    , @NamedQuery(name = "FawnPet.findByName", query = "SELECT f FROM FawnPet f WHERE f.name = :name")
    , @NamedQuery(name = "FawnPet.findByAge", query = "SELECT f FROM FawnPet f WHERE f.age = :age")
    , @NamedQuery(name = "FawnPet.findByGender", query = "SELECT f FROM FawnPet f WHERE f.gender = :gender")
    , @NamedQuery(name = "FawnPet.findByBreeds", query = "SELECT f FROM FawnPet f WHERE f.breeds = :breeds")
    , @NamedQuery(name = "FawnPet.findByPhoto", query = "SELECT f FROM FawnPet f WHERE f.photo = :photo")
    , @NamedQuery(name = "FawnPet.findByVisibility", query = "SELECT f FROM FawnPet f WHERE f.visibility = :visibility")
    , @NamedQuery(name = "FawnPet.findByType", query = "SELECT f FROM FawnPet f WHERE f.type = :type")
    , @NamedQuery(name = "FawnPet.findByMedicalHistory", query = "SELECT f FROM FawnPet f WHERE f.medicalHistory = :medicalHistory")
    , @NamedQuery(name = "FawnPet.findByFamilyTree", query = "SELECT f FROM FawnPet f WHERE f.familyTree = :familyTree")
    , @NamedQuery(name = "FawnPet.findByCertification", query = "SELECT f FROM FawnPet f WHERE f.certification = :certification")})
public class FawnPet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Size(max = 6)
    @Column(name = "gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "breeds")
    private String breeds;
    @Size(max = 350)
    @Column(name = "photo")
    private String photo;
    @Column(name = "visibility")
    private Short visibility;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Size(max = 2500)
    @Column(name = "medicalHistory")
    private String medicalHistory;
    @Size(max = 45)
    @Column(name = "familyTree")
    private String familyTree;
    @Size(max = 350)
    @Column(name = "certification")
    private String certification;
    @JoinColumn(name = "ID_user", referencedColumnName = "ID")
    @ManyToOne
    private FawnUser iDuser;
    @OneToMany(mappedBy = "iDpetReceiver")
    private Collection<FawnPetNotification> fawnPetNotificationCollection;
    @OneToMany(mappedBy = "iDpetSender")
    private Collection<FawnPetNotification> fawnPetNotificationCollection1;

    public FawnPet() {
    }

    public FawnPet(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreeds() {
        return breeds;
    }

    public void setBreeds(String breeds) {
        this.breeds = breeds;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getVisibility() {
        try {
            return visibility == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = (short) (visibility ? 1 : 0);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(String familyTree) {
        this.familyTree = familyTree;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public FawnUser getIDuser() {
        return iDuser;
    }

    public void setIDuser(FawnUser iDuser) {
        this.iDuser = iDuser;
    }

    @XmlTransient
    public Collection<FawnPetNotification> getFawnPetNotificationCollection() {
        return fawnPetNotificationCollection;
    }

    public void setFawnPetNotificationCollection(Collection<FawnPetNotification> fawnPetNotificationCollection) {
        this.fawnPetNotificationCollection = fawnPetNotificationCollection;
    }

    @XmlTransient
    public Collection<FawnPetNotification> getFawnPetNotificationCollection1() {
        return fawnPetNotificationCollection1;
    }

    public void setFawnPetNotificationCollection1(Collection<FawnPetNotification> fawnPetNotificationCollection1) {
        this.fawnPetNotificationCollection1 = fawnPetNotificationCollection1;
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
        if (!(object instanceof FawnPet)) {
            return false;
        }
        FawnPet other = (FawnPet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FawnPet[ id=" + id + " ]";
    }

}
