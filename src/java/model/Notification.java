package model;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
public class Notification {

    private Integer ID,
            ID_Receiver,
            ID_From,
            petID_Receiver,
            petID_From;
    private String status;

    public Notification(Integer ID, Pet sender, Pet receiver) {
        this.ID = ID;
        ID_Receiver = receiver.getIDUser();
        petID_Receiver = receiver.getIDPet();
        ID_From = sender.getIDUser();
        petID_From = sender.getIDPet();
        status = Notification.SENT();
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getID_Receiver() {
        return ID_Receiver;
    }

    public void setID_Receiver(Integer ID_Receiver) {
        this.ID_Receiver = ID_Receiver;
    }

    public Integer getID_From() {
        return ID_From;
    }

    public void setID_From(Integer ID_From) {
        this.ID_From = ID_From;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public static String IDLE () {
        return "";
    }

    public static String SENT () {
        return "sent";
    }
    
    public static String APPROVED () {
        return "approved";
    }
    
    public static String DECLINED () {
        return "declined";
    }

    public Integer getPetID_Receiver() {
        return petID_Receiver;
    }

    public void setPetID_Receiver(Integer petID_Receiver) {
        this.petID_Receiver = petID_Receiver;
    }

    public Integer getPetID_From() {
        return petID_From;
    }

    public void setPetID_From(Integer petID_From) {
        this.petID_From = petID_From;
    }
    
    @Override
    public String toString() {
        return "Notification{" + "ID=" + ID + ", ID_Receiver=" + ID_Receiver + ", ID_From=" + ID_From + ", petID_Receiver=" + petID_Receiver + ", petID_From=" + petID_From + ", status=" + status + '}';
    }
    
}
