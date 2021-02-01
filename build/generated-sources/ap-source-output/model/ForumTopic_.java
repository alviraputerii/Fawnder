package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.ForumPosting;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-24T01:22:32")
@StaticMetamodel(ForumTopic.class)
public class ForumTopic_ { 

    public static volatile CollectionAttribute<ForumTopic, ForumPosting> forumPostingCollection;
    public static volatile SingularAttribute<ForumTopic, String> name;
    public static volatile SingularAttribute<ForumTopic, Integer> id;

}