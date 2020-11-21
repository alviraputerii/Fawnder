package bean;

import dao.DaoNotif;
import dao.DaoPet;
import dao.DaoUser;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import model.Notification;
import model.Pet;
import model.User;
import init.initVar;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable {

    private DaoUser daoUser;
    private DaoNotif daoNotif;
    private DaoPet daoPet;
    private User targetUser;
    private User usermodel;
    private Pet petmodel,
            petFrom,
            petReceiver,
            petRequest;

    public UserBean() {
        initVar var = new initVar();
        daoUser = new DaoUser(var.getDaoUser());
        daoPet = new DaoPet(var.getDaoPet());
        daoNotif = new DaoNotif();
        targetUser = null;
        status = "";
        usermodel = new User();
        petmodel = new Pet();
        petFrom = new Pet();
        petReceiver = new Pet();
        petRequest = null;
    }

    SelectItem[] genderList = {
        new SelectItem("Male"),
        new SelectItem("Female")
    };
    int targetUserID;
    boolean visibility;
    String username,
            password,
            status;

    public SelectItem[] getGenderList() {
        return genderList;
    }

    public void setGenderList(SelectItem[] genderList) {
        this.genderList = genderList;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public DaoUser getDaoUser() {
        return daoUser;
    }

    public void setDaoUser(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public DaoNotif getDaoNotif() {
        return daoNotif;
    }

    public void setDaoNotif(DaoNotif daoNotif) {
        this.daoNotif = daoNotif;
    }

    public DaoPet getDaoPet() {
        return daoPet;
    }

    public void setDaoPet(DaoPet daoPet) {
        this.daoPet = daoPet;
    }

    public int getTargetUserID() {
        return targetUserID;
    }

    public void setTargetUserID(int targetUserID) {
        this.targetUserID = targetUserID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUsermodel() {
        return usermodel;
    }

    public void setUsermodel(User usermodel) {
        this.usermodel = usermodel;
    }

    public Pet getPetmodel() {
        return petmodel;
    }

    public void setPetmodel(Pet petmodel) {
        this.petmodel = petmodel;
    }

    public Pet getPetRequest() {
        return petRequest;
    }

    public void setPetRequest(Pet petRequest) {
        this.petRequest = petRequest;
    }
    
    public void insertPet() {
        User u = new User(daoUser.getLoggedUser());
        petmodel.setIDUser(u.getID());
        daoPet.insertPet(petmodel);
    }

    public void reset() {
        petmodel = new Pet();
    }

    public void sendRequest(Pet p) {
        petFrom = p;
        daoNotif.sendRequest(petFrom, petReceiver);
        setPetRequest(p);
    }

    public Pet getPetFrom() {
        return petFrom;
    }

    public void setPetFrom(Pet petFrom) {
        this.petFrom = petFrom;
    }

    public Pet getPetReceiver() {
        return petReceiver;
    }

    public void setPetReceiver(Pet petReceiver) {
        this.petReceiver = petReceiver;
    }

    public Boolean button_SentRender(Pet p) {
        return daoNotif.buttonRenderSent(daoUser.getLoggedUser(), p).equals(Notification.IDLE());
    }

//    public Boolean button_SentWaitingForApprovalRender(Pet p) {
//        return daoNotif.buttonRenderSent(daoUser.getLoggedUser(), p).equals(Notification.SENT());
//    }
//
//    public Boolean button_SentContactRender(Pet p) {
//        return daoNotif.buttonRenderSent(daoUser.getLoggedUser(), p).equals(Notification.APPROVED());
//    }
//
//    public Boolean button_SentDeclinedRender(Pet p) {
//        return daoNotif.buttonRenderSent(daoUser.getLoggedUser(), p).equals(Notification.DECLINED());
//    }

    public Boolean button_ReceiveRender(Pet p) {
        return daoNotif.buttonRenderReceive(p).equals(Notification.IDLE());
    }

    public Boolean button_ReceiveWaitingForApprovalRender(Pet p) {
        return daoNotif.buttonRenderReceive(p).equals(Notification.SENT());
    }

//    public Boolean button_ReceiveContactRender(Pet p) {
//        return daoNotif.buttonRenderReceive(p).equals(Notification.APPROVED());
//    }
//
//    public Boolean button_ReceiveDeclinedRender(Pet p) {
//        return daoNotif.buttonRenderReceive(p).equals(Notification.DECLINED());
//    }
    
    public Boolean button_AcceptDeclineRender(Notification n) {
        return Notification.SENT().equals(n.getStatus());
    }
    
    public Boolean textDeclinedRender(Notification n) {
        return Notification.DECLINED().equals(n.getStatus());
    }
    
    public Boolean textAcceptedRender(Notification n) {
        return Notification.APPROVED().equals(n.getStatus());
    }
}
