package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderedDishes.class)
public abstract class OrderedDishes_ {

	public static volatile SingularAttribute<OrderedDishes, RestaurantBill> restaurantBill;
	public static volatile SingularAttribute<OrderedDishes, Integer> quantity;
	public static volatile SingularAttribute<OrderedDishes, Integer> id;
	public static volatile SingularAttribute<OrderedDishes, Products> products;

}

