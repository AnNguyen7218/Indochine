package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryOfProduct.class)
public abstract class CategoryOfProduct_ {

	public static volatile SingularAttribute<CategoryOfProduct, Integer> id;
	public static volatile SingularAttribute<CategoryOfProduct, Boolean> isActive;
	public static volatile SingularAttribute<CategoryOfProduct, String> cateName;
	public static volatile SetAttribute<CategoryOfProduct, Products> productses;

}

