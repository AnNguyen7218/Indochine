package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customers.class)
public abstract class Customers_ {

	public static volatile SingularAttribute<Customers, String> customerAddress;
	public static volatile SingularAttribute<Customers, Integer> customerId;
	public static volatile SingularAttribute<Customers, String> identityCard;
	public static volatile SingularAttribute<Customers, String> customerName;
	public static volatile SetAttribute<Customers, BookingRoom> bookingRooms;

}

