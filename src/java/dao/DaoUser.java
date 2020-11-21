package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Pet;
import model.User;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
public class DaoUser {

    User loggedUser;
    List<User> modelUser;
    static List<User> modelUser2;
    Integer IDpointer;

    public DaoUser() {
        modelUser = new ArrayList<>();
        modelUser2 = new ArrayList<>();
        loggedUser = null;
        IDpointer = 0;
    }

    public DaoUser(DaoUser daoUser) {
        this.loggedUser = daoUser.getLoggedUser();
        this.modelUser = daoUser.getAllDataUser();
        this.modelUser2 = daoUser.getAllDataUser2();
        this.IDpointer = daoUser.getIDpointer();
    }
    
    public void setModelUser(List<User> reffModel) {
        modelUser = new ArrayList<>(reffModel);
    }

    public void setModelUser2(List<User> reffModel) {
        modelUser2 = new ArrayList<>(reffModel);
    }
    
    public Integer getIDpointer() {
        return IDpointer;
    }

    public void setIDpointer(Integer IDpointer) {
        this.IDpointer = IDpointer;
    }
    
    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User u) {
        loggedUser = u;
    }
    
    public String insertUser(User u) {
        u.setID(IDpointer++);
        modelUser.add(u);
        modelUser2.add(u);
        return "index";
    }

    public void updateUser(User u) {
        int pointer = 0;
        for (User x : modelUser) {
            if (Objects.equals(x.getID(), u.getID())) {
                break;
            }
            pointer++;
        }
        modelUser.set(pointer, u);
        modelUser2.set(pointer, u);
    }

//    public boolean deleteUser(User u) {
//        int pointer = 0;
//        for (User x : modelUser) {
//            if (Objects.equals(x.getID(), u.getID())) {
//                break;
//            }
//            pointer++;
//        }
//        modelUser.remove(pointer);
//        return true;
//    }

    public List<User> getAllDataUser() {
        return modelUser;
    }
    
    public static List<User> getAllDataUser2() {
        return modelUser2;
    }

    public User getUserByID(Integer ID) {
        for (User u : modelUser) {
            if (Objects.equals(u.getID(), ID)) {
                return u;
            }
        }
        return null;
    }

    public String login(String username, String pass) {
        for (User u : modelUser) {
            if (u.getUsername().equals(username) && u.getPassword().equals(pass)) {
                loggedUser = u;
                
                return "home";
            }
        }
        loggedUser = null;
        return "index";
    }
    
    public boolean checkUsername(String username){
        for (User u : modelUser) {
            if (Objects.equals(u.getUsername(), username)) {
                return false;
            }
        }
        return true;
    }

    public String logout() {
        loggedUser = null;
        return "index";
    }

    public User getUserFromPet(Pet pet) {
        for (User u : modelUser) {
            if (Objects.equals(u.getID(), pet.getIDUser())) {
                return u;
            }
        }
        return null;
    }
}
