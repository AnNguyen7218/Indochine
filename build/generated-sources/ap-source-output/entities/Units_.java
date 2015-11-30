package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Units.class)
public abstract class Units_ {

	public static volatile SingularAttribute<Units, String> unitName;
	public static volatile SingularAttribute<Units, Integer> unitId;
	public static volatile SingularAttribute<Units, Boolean> isActive;
	public static volatile SetAttribute<Units, Products> productses;

}

