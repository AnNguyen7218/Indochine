package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RestaurantTable.class)
public abstract class RestaurantTable_ {

	public static volatile SetAttribute<RestaurantTable, OrderTable> orderTables;
	public static volatile SingularAttribute<RestaurantTable, Integer> numOfChairs;
	public static volatile SetAttribute<RestaurantTable, RestaurantBill> restaurantBills;
	public static volatile SingularAttribute<RestaurantTable, Integer> tableId;
	public static volatile SingularAttribute<RestaurantTable, String> description;
	public static volatile SingularAttribute<RestaurantTable, Boolean> isActive;
	public static volatile SingularAttribute<RestaurantTable, Integer> status;

}

