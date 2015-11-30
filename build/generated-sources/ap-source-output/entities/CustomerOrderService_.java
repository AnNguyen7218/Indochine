package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerOrderService.class)
public abstract class CustomerOrderService_ {

	public static volatile SingularAttribute<CustomerOrderService, Integer> id;
	public static volatile SingularAttribute<CustomerOrderService, String> customerNumber;
	public static volatile SetAttribute<CustomerOrderService, OrderedService> orderedServices;
	public static volatile SingularAttribute<CustomerOrderService, String> customerName;

}

