package entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Products.class)
public abstract class Products_ {

	public static volatile SingularAttribute<Products, CategoryOfProduct> categoryOfProduct;
	public static volatile SingularAttribute<Products, Integer> quantity;
	public static volatile SingularAttribute<Products, Integer> productId;
	public static volatile SingularAttribute<Products, Suppliers> suppliers;
	public static volatile SingularAttribute<Products, Date> importDate;
	public static volatile SingularAttribute<Products, BigDecimal> price;
	public static volatile SingularAttribute<Products, Units> units;
	public static volatile SingularAttribute<Products, Boolean> isActive;
	public static volatile SetAttribute<Products, HotelOrderDish> hotelOrderDishes;
	public static volatile SingularAttribute<Products, String> productName;
	public static volatile SetAttribute<Products, OrderedDishes> orderedDisheses;

}

