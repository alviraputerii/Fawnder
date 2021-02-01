package bean;

import static bean.GlobaManagedBean.addMessage;
import controller.FawnPetNotificationSessionBean;
import controller.FawnPetSessionBean;
import controller.FawnUserSessionBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.FawnPet;
import model.FawnPetNotification;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Named(value = "fawnPetManagedBean")
@SessionScoped
public class FawnPetManagedBean implements Serializable {

    @EJB
    private FawnPetSessionBean petSB;

    @EJB
    private FawnPetNotificationSessionBean notificationSB;

    private final List<String> listStatus;
    private FawnPet selectedPet, editPet;
    private List<FawnPetNotification> sent, received;
    private String domisil, gender, breed;
    private UploadedFile uploadFile;

    public void initUser() {
        sent = notificationSB.getSentNotification();
        received = notificationSB.getReceiveNotification();
    }

    public void resetFilter() {
        domisil = "";
        gender = "-";
        breed = "";
    }

    public FawnPetManagedBean() {
        listStatus = GlobaManagedBean.getStatus();
    }

    public FawnPetSessionBean getPetSB() {
        return petSB;
    }

    public FawnPet getSelectedPet() {
        return selectedPet;
    }

    public void setSelectedPet(FawnPet selectedPet) {
        this.selectedPet = selectedPet;
    }

    public String checkPetRelationSent(FawnPet p) {
        try {
            for (FawnPetNotification t : sent) {
                if (Objects.equals(t.getIDpetSender().getId(), p.getId())) {
                    if (Objects.equals(t.getIDpetReceiver().getId(), selectedPet.getId())) {
                        return t.getStatus();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "idle";
    }

    public String checkPetRelationReceive(FawnPet p) {
        try {
            for (FawnPetNotification t : received) {
                if (Objects.equals(t.getIDpetReceiver().getId(), p.getId())) {
                    if (Objects.equals(t.getIDpetSender().getId(), selectedPet.getId())) {
                        return t.getStatus();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "idle";
    }

    public Boolean booleanIdleSent(FawnPet p) {
        String status = checkPetRelationSent(p);
        return listStatus.get(0).equals(status);
    }

    public Boolean booleanWaitingApprovalSent(FawnPet p) {
        String status = checkPetRelationSent(p);
        return listStatus.get(1).equals(status);
    }

    public Boolean booleanDeclinedSent(FawnPet p) {
        String status = checkPetRelationSent(p);
        return listStatus.get(2).equals(status);
    }

    public Boolean booleanAcceptedSent(FawnPet p) {
        String status = checkPetRelationSent(p);
        return listStatus.get(3).equals(status);
    }

    public Boolean booleanIdleReceive(FawnPet p) {
        String status = checkPetRelationReceive(p);
        return listStatus.get(0).equals(status);
    }

    public Boolean booleanWaitingApprovalReceive(FawnPet p) {
        String status = checkPetRelationReceive(p);
        return listStatus.get(1).equals(status);
    }

    public Boolean booleanDeclinedReceive(FawnPet p) {
        String status = checkPetRelationReceive(p);
        return listStatus.get(2).equals(status);
    }

    public Boolean booleanAcceptedReceive(FawnPet p) {
        String status = checkPetRelationReceive(p);
        return listStatus.get(3).equals(status);
    }

    public void sendRequest(FawnPet p) {
        selectedPet = p;
        GlobaManagedBean.executeScript("PF('dlg7').show();");
    }

    public void choosePet(FawnPet p) {
        FawnPetNotification n = new FawnPetNotification();
        n.setIDpetReceiver(selectedPet);
        n.setIDpetSender(p);
        n.setStatus(listStatus.get(1));
        notificationSB.insert(n);
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Contact request has successfully to '" + selectedPet.getName() + "'");
    }

    public void resend(FawnPet p) {
        for (FawnPetNotification t : sent) {
            if (Objects.equals(t.getIDpetSender().getId(), p.getId())) {
                notificationSB.delete(t);
                t.setStatus(listStatus.get(1));
                notificationSB.insert(t);
                GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Contact request has successfully resend to owner of '" + t.getIDpetReceiver().getName() + "'");
                return;
            }
        }
    }

    public String getDomisil() {
        return domisil;
    }

    public void setDomisil(String domisil) {
        this.domisil = domisil;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<FawnPet> getModelPet() {
        int incrementLength = 0;
        String[] listArgs = new String[0];
        if (!"-".equals(gender) || gender == null) {
            incrementLength++;
            listArgs = Arrays.copyOf(listArgs, incrementLength);
            listArgs[incrementLength - 1] = "gender," + gender;
        }
        if (!"".equals(breed) || breed == null) {
            incrementLength++;
            listArgs = Arrays.copyOf(listArgs, incrementLength);
            listArgs[incrementLength - 1] = "breeds," + breed;
        }
        if (!"".equals(domisil)) {
            incrementLength++;
            listArgs = Arrays.copyOf(listArgs, incrementLength);
            listArgs[incrementLength - 1] = "location," + domisil;
        }
        return petSB.getPetDataFiltered(listArgs);
    }

    public String getManagePetTitle() {
        return editPet == null ? "Add Pet" : "Edit Pet";
    }

    public String getButtonName() {
        return editPet == null ? "Add Pet" : "Save Changes";
    }

    public String initAddPet() {
        editPet = null;
        selectedPet = new FawnPet();
        uploadFile = null;
        return "managePet";
    }

    public String initEditPet(FawnPet p) {
        editPet = p;
        selectedPet = editPet;
        uploadFile = null;
        return "managePet";
    }

    public void deletePet(FawnPet p) {
        petSB.delete(p);
        String fName = p.getPhoto() + ".jpg",
                dest1 = getUserImageDataPath(),
                dest2 = getWorkingUserImageDataPath();
        new File(dest1 + File.separator + fName).delete();
        new File(dest2 + File.separator + fName).delete();
    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUserImageDataPath() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        return path.substring(0, path.length() - 9) + "web" + File.separator + "resources" + File.separator + "userdata" + File.separator + "petimg" + File.separator;
    }

    public String getWorkingUserImageDataPath() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        return path + File.separator + "resources" + File.separator + "userdata" + File.separator + "petimg" + File.separator;
    }

    public void uploadFileListener(FileUploadEvent event) {
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Upload Success");
        uploadFile = event.getFile();
    }

    public String save() throws IOException {
        if (editPet == null) {
            // Add Pet
            selectedPet.setIDuser(FawnUserSessionBean.loggedUser);
            petSB.insert(selectedPet);
            List<FawnPet> model = petSB.getAllData();
            selectedPet = model.get(model.size() - 1);
            selectedPet.setPhoto(String.valueOf(selectedPet.getId()));
            petSB.update(selectedPet);
            changePhoto();
            addMessage(FacesMessage.SEVERITY_INFO, "Add Pet", "Add pet success");
        }
        // Edit Pet
        changePhoto();
        petSB.update(selectedPet);
        addMessage(FacesMessage.SEVERITY_INFO, "Edit Pet", "Edit pet success");
        return "setting";
    }

    public boolean changePhoto() {
        if (uploadFile != null) {
            if (uploadFile.getFileName() != null) {
                String f = FilenameUtils.getExtension(uploadFile.getFileName());
                String filename = String.valueOf(selectedPet.getId());
                try {
                    InputStream input = uploadFile.getInputStream();
                    Files.copy(input, new File(getUserImageDataPath() + File.separator + filename + ".jpg").toPath(), StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(input, new File(getWorkingUserImageDataPath() + File.separator + filename + ".jpg").toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    addMessage(FacesMessage.SEVERITY_INFO, "Edit Profile", e.toString());
                    return false;
                }

            }
        }
        return true;
    }

    public void viewPetInfo(FawnPet p) {
        selectedPet = p;
        PrimeFaces.current().executeScript("PF('petInfo').show();");
    }

    public void setSelectedViewContact(FawnPet p) {
        PrimeFaces.current().executeScript("PF('selectViewContact').show();");
    }
}
