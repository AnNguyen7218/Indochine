package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderTable.class)
public abstract class OrderTable_ {

	public static volatile SingularAttribute<OrderTable, RestaurantTable> restaurantTable;
	public static volatile SingularAttribute<OrderTable, Date> dateIn;
	public static volatile SingularAttribute<OrderTable, Integer> orderID;
	public static volatile SingularAttribute<OrderTable, Date> dateOrder;
	public static volatile SingularAttribute<OrderTable, Integer> id;
	public static volatile SingularAttribute<OrderTable, Accounts> accounts;
	public static volatile SingularAttribute<OrderTable, CustomerOrderTable> customerOrderTable;

}

