package entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RestaurantBill.class)
public abstract class RestaurantBill_ {

	public static volatile SingularAttribute<RestaurantBill, RestaurantTable> restaurantTable;
	public static volatile SingularAttribute<RestaurantBill, Date> date;
	public static volatile SingularAttribute<RestaurantBill, BigDecimal> total;
	public static volatile SingularAttribute<RestaurantBill, Integer> id;
	public static volatile SingularAttribute<RestaurantBill, Accounts> accounts;
	public static volatile SingularAttribute<RestaurantBill, String> customerName;
	public static volatile SetAttribute<RestaurantBill, OrderedDishes> orderedDisheses;

}

