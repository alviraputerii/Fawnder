package bean;

import controller.FawnUserSessionBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import model.FawnUser;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Named(value = "fawnUserManagedBean")
@SessionScoped
public class FawnUserManagedBean implements Serializable {

    @EJB
    private FawnUserSessionBean userSB;

    private String loginUsername;
    private String loginPassword;
    private FawnUser editUser;
    private boolean isRegister;

    public void init() {
        loginUsername = "";
        loginPassword = "";
        editUser = null;
        isRegister = false;
        try {
            userSB.logout();
        } catch (Exception e) {
        }
    }

    public void initEdit() {
        editUser = new FawnUser(userSB.getLoggedUser());
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

    public String login() {
        Boolean loginStatus = userSB.login(loginUsername, loginPassword);
        if (loginStatus) {
            return "home";
        }
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Username / Password is incorrect.");
        init();
        return "";
    }

    public String logout() {
        init();
        return "login";
    }

    public FawnUserSessionBean getUserSB() {
        return userSB;
    }

    public void resetEditUser() {
        editUser = userSB.getLoggedUser();
    }

    public FawnUser getEditUser() {
        return editUser;
    }

    public void setEditUser(FawnUser editUser) {
        this.editUser = editUser;
    }

    public void edit() {
        if (!editUser.getLoginUsername().equals(FawnUserSessionBean.loggedUser.getLoginUsername())) {
            if (userSB.checkUsernameAlreadyExist(editUser.getLoginUsername())) {
                GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Fail to save profile data, username already exist");
                return;
            }
        }
        userSB.update(editUser);
        userSB.login(editUser.getLoginUsername(), editUser.getLoginPassword());
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Profile data has been updated");
    }

    public void initRegistration() {
        editUser = new FawnUser();
        isRegister = true;
    }

    public void registerU() {
        if (userSB.checkUsernameAlreadyExist(editUser.getLoginUsername())) {
            GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Fail to register, username already exist");
            return;
        }
        userSB.insert(editUser);
        init();
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Register Success");
    }

    public boolean isIsRegister() {
        return isRegister;
    }

    public void setIsRegister(boolean isRegister) {
        this.isRegister = isRegister;
    }

    public void toLogin() {
        init();
    }

    public FawnUser getLoggedUser() {
        return FawnUserSessionBean.loggedUser;
    }
}
