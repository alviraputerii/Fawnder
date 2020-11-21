package model;

/**
 *
 * @author 1873004-Alvira Puteri
 */
public class Pet {

    private Integer IDPet,
            IDUser,
            petAge;
    private String petName,
            petGender,
            petBreeds,
            photo;
    private boolean visibility;

    public Pet(Integer IDUser, Integer petAge, String petName, String petGender, String petBreeds,  boolean visibility) {
        this.petAge = petAge;
        this.petName = petName;
        this.petGender = petGender;
        this.petBreeds = petBreeds;
        this.photo = petBreeds;
        this.visibility = visibility;
        this.IDUser = IDUser;
    }

    public Pet() {
    }
    
    public Integer getIDPet() {
        return IDPet;
    }

    public void setIDPet(Integer IDPet) {
        this.IDPet = IDPet;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetBreeds() {
        return petBreeds;
    }

    public void setPetBreeds(String petBreeds) {
        this.petBreeds = petBreeds;
    }

    public String getPhoto() {
        return petBreeds;
    }

    public void setPhoto() {
        this.photo = getPetBreeds();
    }

    public boolean isVisibility() {
        return visibility;
    }

    public String getVisibility2() {
        return visibility ? "visible" : "invisible";
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Integer getIDUser() {
        return IDUser;
    }

    public void setIDUser(Integer IDUser) {
        this.IDUser = IDUser;
    }

    @Override
    public String toString() {
        return "Pet{" + "IDPet=" + IDPet + ", IDUser=" + IDUser + ", petName=" + petName + '}';
    }
}
