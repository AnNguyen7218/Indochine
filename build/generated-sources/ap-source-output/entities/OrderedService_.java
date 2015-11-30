package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderedService.class)
public abstract class OrderedService_ {

	public static volatile SingularAttribute<OrderedService, CustomerOrderService> customerOrderService;
	public static volatile SingularAttribute<OrderedService, Integer> quantity;
	public static volatile SingularAttribute<OrderedService, Integer> orderId;
	public static volatile SingularAttribute<OrderedService, Date> dateOrder;
	public static volatile SingularAttribute<OrderedService, Integer> id;
	public static volatile SingularAttribute<OrderedService, Accounts> accounts;
	public static volatile SingularAttribute<OrderedService, Services> services;

}

