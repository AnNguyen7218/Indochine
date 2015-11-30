package entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BookingRoom.class)
public abstract class BookingRoom_ {

	public static volatile SingularAttribute<BookingRoom, Date> dateIn;
	public static volatile SetAttribute<BookingRoom, BookedRooms> bookedRoomses;
	public static volatile SingularAttribute<BookingRoom, Date> dateOut;
	public static volatile SingularAttribute<BookingRoom, Integer> id;
	public static volatile SingularAttribute<BookingRoom, Accounts> accounts;
	public static volatile SingularAttribute<BookingRoom, Customers> customers;
	public static volatile SingularAttribute<BookingRoom, Integer> numOfChildren;
	public static volatile SingularAttribute<BookingRoom, Integer> numOfAdult;
	public static volatile SingularAttribute<BookingRoom, BigDecimal> advance;

}

