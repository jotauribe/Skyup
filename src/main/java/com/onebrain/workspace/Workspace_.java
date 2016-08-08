package com.onebrain.workspace;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-08T09:38:39.328-0500")
@StaticMetamodel(Workspace.class)
public class Workspace_ {
	public static volatile SingularAttribute<Workspace, Integer> id;
	public static volatile SingularAttribute<Workspace, String> name;
	public static volatile SingularAttribute<Workspace, String> description;
	public static volatile SingularAttribute<Workspace, AccessList> accesslist;
}
