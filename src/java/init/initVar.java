package init;

import dao.DaoPet;
import dao.DaoUser;
import model.Pet;
import model.User;

/**
 *
 * @author 1873004 - Alvira Puteri
 */
public class initVar {

    private DaoUser daoUser;
    private DaoPet daoPet;

    public initVar() {
        daoUser = new DaoUser();
        daoPet = new DaoPet();
        User u;

        daoUser.insertUser(new User("candycin", "12345", "Cindy", "082127626977", "Cimahi")); 
        daoPet.insertPet(new Pet(0, 10, "Schera", "female", "Akita", true));

        daoUser.insertUser(new User("feylice_", "12345", "Fey", "082125363547", "Jakarta"));
        daoPet.insertPet(new Pet(1, 15, "Sakura", "female", "Alaskan Malamute",true));

        daoUser.insertUser(new User("junono", "12345", "Juno", "082183527389", "Bekasi"));
        daoPet.insertPet(new Pet(2, 12, "Junko", "male", "Golden Retriever", true));

        daoUser.insertUser(new User("pimnatcha", "12345", "Pim", "089825384692", "Bandung"));
        daoPet.insertPet(new Pet(3, 11, "clovisy", "female", "Poodle", true));

        daoUser.insertUser(new User("louisf", "12345", "Louis", "085926340876", "Bogor"));
        daoPet.insertPet(new Pet(4, 14, "Shin", "male", "Dalmatian",  true));
        daoPet.insertPet(new Pet(4, 13, "Shou", "male", "Bulldog",  true));

        daoUser.insertUser(new User("carolinemey", "12345", "Meyka", "087307243946", "Medan"));
        daoPet.insertPet(new Pet(5, 17, "Miko", "female", "Chihuahua",  true));
    }

    public DaoUser getDaoUser() {
        return daoUser;
    }

    public void setDaoUser(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public DaoPet getDaoPet() {
        return daoPet;
    }

    public void setDaoPet(DaoPet daoPet) {
        this.daoPet = daoPet;
    }
}
