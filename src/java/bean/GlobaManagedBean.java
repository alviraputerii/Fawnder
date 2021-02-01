package bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Named(value = "globalManagedBean")
@SessionScoped
public class GlobaManagedBean implements Serializable {

    public static void addMessage(FacesMessage.Severity facesMessage_Severity, String title, String detail) {
        FacesMessage message = new FacesMessage(facesMessage_Severity, title, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void executeScript(String script) {
        PrimeFaces.current().executeScript(script);
    }

    public static List<String> getStatus() {
        return new ArrayList<>(Arrays.asList("idle", "waitingApproval", "declined", "accepted"));
    }

    public String getUserImgData(String fname) {
        String path = "/resources/userdata/petimg/" + fname + ".jpg";
        return path;
    }

    public String getAltErrPetImgData() {
        String path = "/resources/appdata/LogoTf.png";
        return path;
    }

    public String getFilteredForumContent(String val) {
        if (val.length() > 80) {
            return val.substring(0, 65) + "...";
        }
        return val;
    }

    public String getDateForumConverted(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
