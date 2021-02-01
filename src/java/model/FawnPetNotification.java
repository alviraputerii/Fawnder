package model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Entity
@Table(name = "fawn_pet_notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FawnPetNotification.findAll", query = "SELECT f FROM FawnPetNotification f")
    , @NamedQuery(name = "FawnPetNotification.findById", query = "SELECT f FROM FawnPetNotification f WHERE f.id = :id")
    , @NamedQuery(name = "FawnPetNotification.findByStatus", query = "SELECT f FROM FawnPetNotification f WHERE f.status = :status")})
public class FawnPetNotification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "ID_petReceiver", referencedColumnName = "ID")
    @ManyToOne
    private FawnPet iDpetReceiver;
    @JoinColumn(name = "ID_petSender", referencedColumnName = "ID")
    @ManyToOne
    private FawnPet iDpetSender;

    public FawnPetNotification() {
    }

    public FawnPetNotification(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FawnPet getIDpetReceiver() {
        return iDpetReceiver;
    }

    public void setIDpetReceiver(FawnPet iDpetReceiver) {
        this.iDpetReceiver = iDpetReceiver;
    }

    public FawnPet getIDpetSender() {
        return iDpetSender;
    }

    public void setIDpetSender(FawnPet iDpetSender) {
        this.iDpetSender = iDpetSender;
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
        if (!(object instanceof FawnPetNotification)) {
            return false;
        }
        FawnPetNotification other = (FawnPetNotification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FawnPetNotification[ id=" + id + " ]";
    }

}
