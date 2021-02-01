package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.FawnPet;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-24T01:22:32")
@StaticMetamodel(FawnPetNotification.class)
public class FawnPetNotification_ { 

    public static volatile SingularAttribute<FawnPetNotification, FawnPet> iDpetSender;
    public static volatile SingularAttribute<FawnPetNotification, Integer> id;
    public static volatile SingularAttribute<FawnPetNotification, FawnPet> iDpetReceiver;
    public static volatile SingularAttribute<FawnPetNotification, String> status;

}