package filter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
public class PetFilter {

    private String petBreeds,
            location;
    private String gender;

    public PetFilter() {
        petBreeds = "";
        location = "";
        gender = "all";
    }

    public String getPetBreeds() {
        return petBreeds;
    }

    public void setPetBreeds(String petBreeds) {
        this.petBreeds = petBreeds.toLowerCase();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location.toLowerCase();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.toLowerCase();
    }
    
    public boolean checker(String query, String container) {
        String[] list = query.split(",");
        List<String> asd = new ArrayList<>();
        for (String s : list) {
            asd.add(s.replaceAll(" ", "").toLowerCase());
        }
        for (String s : asd) {
            if (container.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        petBreeds = "";
        location = "";
        gender = "all";
    }
}
