package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Roles.class)
public abstract class Roles_ {

	public static volatile SetAttribute<Roles, RoleFunction> roleFunctions;
	public static volatile SingularAttribute<Roles, Integer> roleId;
	public static volatile SingularAttribute<Roles, String> roleName;
	public static volatile SetAttribute<Roles, Accounts> accountses;
	public static volatile SingularAttribute<Roles, Boolean> isActive;

}

