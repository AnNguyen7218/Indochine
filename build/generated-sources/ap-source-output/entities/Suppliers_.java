package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Suppliers.class)
public abstract class Suppliers_ {

	public static volatile SingularAttribute<Suppliers, String> supplierName;
	public static volatile SingularAttribute<Suppliers, Integer> supplierId;
	public static volatile SingularAttribute<Suppliers, Boolean> isActive;
	public static volatile SetAttribute<Suppliers, OrderForSupplier> orderForSuppliers;
	public static volatile SetAttribute<Suppliers, Products> productses;

}

