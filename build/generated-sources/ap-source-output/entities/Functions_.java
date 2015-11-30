package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Functions.class)
public abstract class Functions_ {

	public static volatile SetAttribute<Functions, RoleFunction> roleFunctions;
	public static volatile SingularAttribute<Functions, Integer> functionId;
	public static volatile SingularAttribute<Functions, String> functionName;
	public static volatile SingularAttribute<Functions, Boolean> isActive;

}

