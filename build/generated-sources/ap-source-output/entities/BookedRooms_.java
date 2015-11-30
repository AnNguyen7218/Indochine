package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BookedRooms.class)
public abstract class BookedRooms_ {

	public static volatile SingularAttribute<BookedRooms, BookingRoom> bookingRoom;
	public static volatile SingularAttribute<BookedRooms, Rooms> rooms;
	public static volatile SingularAttribute<BookedRooms, Integer> id;

}

