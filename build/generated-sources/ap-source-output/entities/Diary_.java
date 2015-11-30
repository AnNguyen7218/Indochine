package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Diary.class)
public abstract class Diary_ {

	public static volatile SingularAttribute<Diary, Date> dateTime;
	public static volatile SingularAttribute<Diary, Integer> diaryId;
	public static volatile SingularAttribute<Diary, String> description;
	public static volatile SingularAttribute<Diary, Accounts> accounts;

}

