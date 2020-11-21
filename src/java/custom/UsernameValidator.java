package custom;

import dao.DaoUser;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import model.User;

/**
 *
 * @author 1873004 - Alvira Puteri
 */

@FacesValidator("model.Username")
public class UsernameValidator implements Validator,Serializable {

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object obj) throws ValidatorException {
        
        List<User> modelUser = DaoUser.getAllDataUser2();
        for (User user : modelUser) {
            if (user.getUsername().equals((String) obj)) {
                FacesMessage message = new FacesMessage();
                message.setDetail("Username already exist");
                message.setSummary("Username already exist");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    } 
}
