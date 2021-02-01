package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.FawnUser;
import model.ForumPostingComment;
import model.ForumTopic;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-24T01:22:32")
@StaticMetamodel(ForumPosting.class)
public class ForumPosting_ { 

    public static volatile SingularAttribute<ForumPosting, Date> date;
    public static volatile CollectionAttribute<ForumPosting, ForumPostingComment> forumPostingCommentCollection;
    public static volatile SingularAttribute<ForumPosting, FawnUser> iDuser;
    public static volatile SingularAttribute<ForumPosting, String> subject;
    public static volatile SingularAttribute<ForumPosting, Short> hasEdit;
    public static volatile SingularAttribute<ForumPosting, ForumTopic> iDtopic;
    public static volatile SingularAttribute<ForumPosting, Integer> id;
    public static volatile SingularAttribute<ForumPosting, Date> editDate;
    public static volatile SingularAttribute<ForumPosting, String> content;

}