package entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Services.class)
public abstract class Services_ {

	public static volatile SetAttribute<Services, HotelOrderService> hotelOrderServices;
	public static volatile SingularAttribute<Services, BigDecimal> price;
	public static volatile SingularAttribute<Services, Integer> serviceId;
	public static volatile SingularAttribute<Services, String> serviceName;
	public static volatile SingularAttribute<Services, Boolean> isActive;
	public static volatile SetAttribute<Services, OrderedService> orderedServices;

}

