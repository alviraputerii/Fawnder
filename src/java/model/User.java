package model;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
public class User {

    private Integer ID;
    private String username,
            password,
            nameOwner,
            contactOwner,
            location;

    public User(String username, String password, String nameOwner, String contactOwner, String location) {
        this.username = username;
        this.password = password;
        this.nameOwner = nameOwner;
        this.contactOwner = contactOwner;
        this.location = location;
    }

    public User() {
    }

    public User(User u) {
        ID = u.getID();
        username = u.getUsername();
        password = u.getPassword();
        nameOwner = u.getNameOwner();
        contactOwner = u.getContactOwner();
        location = u.getLocation();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getContactOwner() {
        return contactOwner;
    }

    public void setContactOwner(String contactOwner) {
        this.contactOwner = contactOwner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", username=" + username + '}';
    }
    
    
}
