package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryOfRoom.class)
public abstract class CategoryOfRoom_ {

	public static volatile SingularAttribute<CategoryOfRoom, String> cateRoomName;
	public static volatile SingularAttribute<CategoryOfRoom, Long> size;
	public static volatile SingularAttribute<CategoryOfRoom, Long> price;
	public static volatile SingularAttribute<CategoryOfRoom, Integer> numOfBed;
	public static volatile SetAttribute<CategoryOfRoom, Rooms> roomses;
	public static volatile SingularAttribute<CategoryOfRoom, Integer> id;
	public static volatile SingularAttribute<CategoryOfRoom, Boolean> isActive;

}

