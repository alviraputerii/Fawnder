/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import filter.PetFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.Pet;
import model.User;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
public class DaoPet {

    private List<Pet> listPet;
    private List<Pet> managePet;
    private Integer IDpointer;
    private PetFilter petFilter;

    public DaoPet() {
        listPet = new ArrayList<>();
        managePet = new ArrayList<>();
        petFilter = new PetFilter();
        IDpointer = 0;
    }

    public DaoPet(DaoPet daoPet) {
        this.listPet = daoPet.getListPet();
        this.managePet = daoPet.getManagePet();
        this.IDpointer = daoPet.getIDpointer();
        this.petFilter = daoPet.getPetFilter();
    }

    public List<Pet> getListPet() {
        return listPet;
    }

    public void setListPet(List<Pet> listPet) {
        this.listPet = listPet;
    }

    public List<Pet> getManagePet() {
        return managePet;
    }

    public void setManagePet(List<Pet> managePet) {
        this.managePet = managePet;
    }

    public Integer getIDpointer() {
        return IDpointer;
    }

    public void setIDpointer(Integer IDpointer) {
        this.IDpointer = IDpointer;
    }

    public PetFilter getPetFilter() {
        return petFilter;
    }

    public void setPetFilter(PetFilter petFilter) {
        this.petFilter = petFilter;
    }

    public void insertPet(Pet pet) {
        pet.setIDPet(IDpointer++);
        listPet.add(pet);
    }

    public boolean updatePet(Pet p) {
        int pointer = 0;
        for (Pet x : listPet) {
            if (Objects.equals(x.getIDPet(), p.getIDPet())) {
                break;
            }
            pointer++;
        }
        listPet.set(pointer, p);
        return true;
    }

    public Pet getPetByID(Integer petID) {
        for (Pet pet : listPet) {
            if (Objects.equals(petID, pet.getIDPet())) {
                return pet;
            }
        }
        return null;
    }

    public List<Pet> getAllDataPet() {
        return listPet;
    }

    public List<Pet> getDataPetByUser(User u) {
        List<Pet> output = new ArrayList<>();
        for (Pet p : getAllDataPet()) {
            if (Objects.equals(u.getID(), p.getIDUser())) {
                output.add(p);
            }
        }
        return output;
    }

    public List<Pet> getAllDataUserFiltered(User loggedUser, List<User> modelUser) {
        List<Pet> filteredCommon;
        List<Pet> petFromUser;
        List<User> commonFilter;
        if (!"".equals(petFilter.getLocation()) || null != petFilter.getLocation()) {
            commonFilter = new ArrayList<>();
            for (User u : modelUser) {
                // Filter Logged User
                if (!Objects.equals(loggedUser.getID(), u.getID())) {
                    // Filter Location
                    if (petFilter.checker(petFilter.getLocation(), u.getLocation().toLowerCase())) {
                        commonFilter.add(u);
                    }
                }
            }
            petFromUser = new ArrayList<>();
            for (User u : commonFilter) {
                for (Pet p : getAllDataPet()) {
                    if (Objects.equals(u.getID(), p.getIDUser())) {
                        // Filter Visibility
                        if (p.isVisibility()) {
                            petFromUser.add(p);
                        }
                    }
                }
            }
            filteredCommon = new ArrayList<>(petFromUser);
        } else {
            filteredCommon = new ArrayList<>(listPet);
        }

        Predicate<Pet> byGender = petGender -> petFilter.getGender().toLowerCase().equals(petGender.getPetGender().toLowerCase());
        List<Pet> filteredByGender;
        if (!"all".equals(petFilter.getGender())) {
            filteredByGender = new ArrayList<>(filteredCommon.stream().filter(byGender).collect(Collectors.toList()));
        } else {
            filteredByGender = new ArrayList<>(filteredCommon);
        }

        List<Pet> filteredBreeds = new ArrayList<>();
        if (!"".equals(petFilter.getGender()) || null != petFilter.getGender()) {
            for (Pet pet : filteredByGender) {
                if (petFilter.checker(petFilter.getPetBreeds(), pet.getPetBreeds().toLowerCase())) {
                    filteredBreeds.add(pet);
                }
            }
        } else {
            filteredBreeds = new ArrayList<>(filteredByGender);
        }
        return filteredBreeds;
    }
}
