package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HotelOrderService.class)
public abstract class HotelOrderService_ {

	public static volatile SingularAttribute<HotelOrderService, Rooms> rooms;
	public static volatile SingularAttribute<HotelOrderService, Integer> quantity;
	public static volatile SingularAttribute<HotelOrderService, Integer> id;
	public static volatile SingularAttribute<HotelOrderService, Services> services;

}

