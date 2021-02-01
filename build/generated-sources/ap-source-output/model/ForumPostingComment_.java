package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.FawnUser;
import model.ForumPosting;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-24T01:22:32")
@StaticMetamodel(ForumPostingComment.class)
public class ForumPostingComment_ { 

    public static volatile SingularAttribute<ForumPostingComment, Date> date;
    public static volatile SingularAttribute<ForumPostingComment, FawnUser> iDuser;
    public static volatile SingularAttribute<ForumPostingComment, Short> hasEdit;
    public static volatile SingularAttribute<ForumPostingComment, ForumPosting> iDposting;
    public static volatile SingularAttribute<ForumPostingComment, Integer> id;
    public static volatile SingularAttribute<ForumPostingComment, Date> editDate;
    public static volatile SingularAttribute<ForumPostingComment, String> content;

}