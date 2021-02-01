package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.FawnPetNotification;
import model.FawnUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-24T01:22:32")
@StaticMetamodel(FawnPet.class)
public class FawnPet_ { 

    public static volatile SingularAttribute<FawnPet, String> gender;
    public static volatile SingularAttribute<FawnPet, Short> visibility;
    public static volatile SingularAttribute<FawnPet, FawnUser> iDuser;
    public static volatile SingularAttribute<FawnPet, String> photo;
    public static volatile SingularAttribute<FawnPet, String> type;
    public static volatile SingularAttribute<FawnPet, String> breeds;
    public static volatile SingularAttribute<FawnPet, String> certification;
    public static volatile CollectionAttribute<FawnPet, FawnPetNotification> fawnPetNotificationCollection;
    public static volatile CollectionAttribute<FawnPet, FawnPetNotification> fawnPetNotificationCollection1;
    public static volatile SingularAttribute<FawnPet, String> name;
    public static volatile SingularAttribute<FawnPet, String> familyTree;
    public static volatile SingularAttribute<FawnPet, Integer> id;
    public static volatile SingularAttribute<FawnPet, String> medicalHistory;
    public static volatile SingularAttribute<FawnPet, Integer> age;

}