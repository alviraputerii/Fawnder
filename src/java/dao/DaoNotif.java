/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Notification;
import model.Pet;
import model.User;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
public class DaoNotif implements Serializable{

    private List<Notification> modelNotif;
    private Integer pointerInOut;
    User owner;
    
    public DaoNotif() {
        modelNotif = new ArrayList<>();
        pointerInOut = 0;
        owner = null;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void sendRequest(Pet psender, Pet preceiver) {
        modelNotif.add(new Notification(pointerInOut++, psender, preceiver));
    }
    
    public void acceptRequest(Notification not, User owner) {
        not.setStatus(Notification.APPROVED());
        updateNotification(not);
        setOwner(owner);
    }
    
    public void declineRequest(Notification not) {
        not.setStatus(Notification.DECLINED());
        updateNotification(not);
    }
    
    public List<Notification> getSentNotification(User loggedUser) {
        List<Notification> output = new ArrayList<>();
        for (Notification n : modelNotif) {
            if (Objects.equals(n.getID_From(), loggedUser.getID())) {
                output.add(n);
            }
        }
        return output;
    }
    
    public List<Notification> getReceiveNotification(Integer userReceiverID) {
        List<Notification> output = new ArrayList<>();
        for (Notification n : modelNotif) {
            if (Objects.equals(n.getID_Receiver(), userReceiverID)) {
                output.add(n);
            }
        }
        return output;
    }
    
    public String buttonRenderSent(User loggedUser, Pet pSender) {
        for (Notification n : getSentNotification(loggedUser)) {
            if (Objects.equals(n.getPetID_From(), pSender.getIDPet())) {
                return n.getStatus();
            }
        }
        return Notification.IDLE();
    }
    
    public String buttonRenderReceive(Pet pReceiver) {
        for (Notification n : getReceiveNotification(pReceiver.getIDUser())) {
            if (Objects.equals(n.getID_Receiver(), pReceiver.getIDPet())) {
                return n.getStatus();
            }
        }
        return Notification.IDLE();
    }
    
    public void updateNotification(Notification not) {
        Integer pointer = 0;
        for (Notification n : modelNotif) {
            if (Objects.equals(n.getID(), not.getID())) {
                break;
            }
            pointer++;
        }
        modelNotif.set(pointer, not);
    }
}
