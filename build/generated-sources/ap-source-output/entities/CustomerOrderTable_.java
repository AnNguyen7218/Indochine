package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerOrderTable.class)
public abstract class CustomerOrderTable_ {

	public static volatile SetAttribute<CustomerOrderTable, OrderTable> orderTables;
	public static volatile SingularAttribute<CustomerOrderTable, Integer> id;
	public static volatile SingularAttribute<CustomerOrderTable, String> customerNumber;
	public static volatile SingularAttribute<CustomerOrderTable, String> customerName;

}

