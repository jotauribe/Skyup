package com.onebrain.content;

import com.onebrain.user.User;
import com.onebrain.workspace.Workspace;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-08T20:10:00.636-0500")
@StaticMetamodel(Idea.class)
public class Idea_ {
	public static volatile SingularAttribute<Idea, Integer> id;
	public static volatile SingularAttribute<Idea, String> title;
	public static volatile SingularAttribute<Idea, String> description;
	public static volatile SingularAttribute<Idea, User> author;
	public static volatile SingularAttribute<Idea, Workspace> workspace;
}
