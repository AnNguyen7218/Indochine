package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderForSupplier.class)
public abstract class OrderForSupplier_ {

	public static volatile SingularAttribute<OrderForSupplier, Suppliers> suppliers;
	public static volatile SetAttribute<OrderForSupplier, OrderLineForSupplier> orderLineForSuppliers;
	public static volatile SingularAttribute<OrderForSupplier, String> dateOrder;
	public static volatile SingularAttribute<OrderForSupplier, Integer> id;
	public static volatile SingularAttribute<OrderForSupplier, Accounts> accounts;

}

