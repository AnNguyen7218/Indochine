package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rooms.class)
public abstract class Rooms_ {

	public static volatile SetAttribute<Rooms, HotelOrderService> hotelOrderServices;
	public static volatile SetAttribute<Rooms, BookedRooms> bookedRoomses;
	public static volatile SingularAttribute<Rooms, CategoryOfRoom> categoryOfRoom;
	public static volatile SingularAttribute<Rooms, Integer> floor;
	public static volatile SingularAttribute<Rooms, Boolean> isActive;
	public static volatile SetAttribute<Rooms, HotelOrderDish> hotelOrderDishes;
	public static volatile SingularAttribute<Rooms, Integer> roomId;
	public static volatile SingularAttribute<Rooms, String> roomName;
	public static volatile SingularAttribute<Rooms, Integer> status;

}

