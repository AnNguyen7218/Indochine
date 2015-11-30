package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Accounts.class)
public abstract class Accounts_ {

	public static volatile SingularAttribute<Accounts, String> accountPass;
	public static volatile SingularAttribute<Accounts, Integer> accountId;
	public static volatile SetAttribute<Accounts, OrderTable> orderTables;
	public static volatile SingularAttribute<Accounts, String> accountName;
	public static volatile SingularAttribute<Accounts, Roles> roles;
	public static volatile SetAttribute<Accounts, RestaurantBill> restaurantBills;
	public static volatile SetAttribute<Accounts, Diary> diaries;
	public static volatile SingularAttribute<Accounts, Boolean> isActive;
	public static volatile SetAttribute<Accounts, OrderedService> orderedServices;
	public static volatile SetAttribute<Accounts, OrderForSupplier> orderForSuppliers;
	public static volatile SetAttribute<Accounts, BookingRoom> bookingRooms;

}

