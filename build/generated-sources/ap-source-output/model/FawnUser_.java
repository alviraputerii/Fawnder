package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.FawnPet;
import model.ForumPosting;
import model.ForumPostingComment;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-24T01:22:32")
@StaticMetamodel(FawnUser.class)
public class FawnUser_ { 

    public static volatile CollectionAttribute<FawnUser, ForumPostingComment> forumPostingCommentCollection;
    public static volatile SingularAttribute<FawnUser, String> loginUsername;
    public static volatile CollectionAttribute<FawnUser, ForumPosting> forumPostingCollection;
    public static volatile CollectionAttribute<FawnUser, FawnPet> fawnPetCollection;
    public static volatile SingularAttribute<FawnUser, String> contact;
    public static volatile SingularAttribute<FawnUser, String> loginPassword;
    public static volatile SingularAttribute<FawnUser, String> name;
    public static volatile SingularAttribute<FawnUser, String> location;
    public static volatile SingularAttribute<FawnUser, Integer> id;

}