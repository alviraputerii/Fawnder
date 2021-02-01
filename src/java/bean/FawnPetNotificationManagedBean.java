package bean;

import controller.FawnPetNotificationSessionBean;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import model.FawnPetNotification;
import org.primefaces.PrimeFaces;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Named(value = "fawnPetNotificationManagedBean")
@SessionScoped
public class FawnPetNotificationManagedBean implements Serializable {

    @EJB
    private FawnPetNotificationSessionBean petNotificationSB;

    private FawnPetNotification selectedNotification;
    private final List<String> listStatus;

    public FawnPetNotificationManagedBean() {
        listStatus = GlobaManagedBean.getStatus();
    }

    public void init() {
        selectedNotification = null;
    }

    public FawnPetNotificationSessionBean getPetNotificationSB() {
        return petNotificationSB;
    }

    public void setSelectedNotificationSender(FawnPetNotification n) {
        this.selectedNotification = n;
        PrimeFaces.current().executeScript("PF('senderViewContact').show();");
    }

    public void setSelectedNotificationReceiver(FawnPetNotification n) {
        this.selectedNotification = n;
        PrimeFaces.current().executeScript("PF('petInfoApproval').show();");
    }

    public FawnPetNotification getCurrNotifications() {
        return selectedNotification;
    }

    public Boolean booleanWaitingApproval(FawnPetNotification n) {
        return listStatus.get(1).equals(n.getStatus());
    }

    public Boolean booleanDeclined(FawnPetNotification n) {
        return listStatus.get(2).equals(n.getStatus());
    }

    public Boolean booleanAccepted(FawnPetNotification n) {
        return listStatus.get(3).equals(n.getStatus());
    }

    public void approve(FawnPetNotification n) {
        n.setStatus(listStatus.get(3));
        petNotificationSB.update(n);
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Contact request from '" + n.getIDpetSender().getName() + "' has successfully approved");
    }

    public void decline(FawnPetNotification n) {
        n.setStatus(listStatus.get(2));
        petNotificationSB.update(n);
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Contact request from '" + n.getIDpetSender().getName() + "' has successfully declined");
    }

    public void resend(FawnPetNotification n) {
        petNotificationSB.delete(n);
        n.setId(null);
        n.setStatus(listStatus.get(1));
        petNotificationSB.insert(n);
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Contact request has successfully resend to owner of '" + n.getIDpetReceiver().getName() + "'");
    }

    public FawnPetNotification getSelectedNotification() {
        return selectedNotification;
    }

    public void setSelectedNotification(FawnPetNotification selectedNotification) {
        this.selectedNotification = selectedNotification;
    }

}
