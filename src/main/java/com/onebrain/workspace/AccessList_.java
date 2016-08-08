package com.onebrain.workspace;

import com.onebrain.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-07T21:49:07.746-0500")
@StaticMetamodel(AccessList.class)
public class AccessList_ {
	public static volatile SingularAttribute<AccessList, Integer> id;
	public static volatile SingularAttribute<AccessList, User> owner;
	public static volatile ListAttribute<AccessList, User> members;
}
