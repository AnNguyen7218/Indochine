package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HotelOrderDish.class)
public abstract class HotelOrderDish_ {

	public static volatile SingularAttribute<HotelOrderDish, Rooms> rooms;
	public static volatile SingularAttribute<HotelOrderDish, Integer> quantity;
	public static volatile SingularAttribute<HotelOrderDish, Integer> id;
	public static volatile SingularAttribute<HotelOrderDish, Products> products;

}

