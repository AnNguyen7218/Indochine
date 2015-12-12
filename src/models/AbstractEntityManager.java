/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author lehai
 */

import utils.HibernateUtil;
import java.util.*;
import org.hibernate.*;

/**
 * Data Access Object Manage create , read, update, delete entities.
 *
 * @author lehai
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class AbstractEntityManager<T> {

    private final Class<T> entityClass;
    //tạo 1 session, làm liên kết vật lý đên db, session obj liên tục lưu lại và
    //lấy thông tin của đối tượng cần
    
    
    protected final SessionFactory sessionFactory = HibernateUtil
            .getSessionFactory();

    public AbstractEntityManager(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> getAll() {
        try {
            //khởi tạo ss khi tương tác cần thiết
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            
            //from + table name (ten bang trung ten class nen getName())
            return sessionFactory.getCurrentSession()
                    .createQuery("from " + entityClass.getName()).list();
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
            return null;
        }
    }

    public boolean update(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().merge(instance);

            sessionFactory.getCurrentSession().getTransaction().commit();
            return true;
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }


    public boolean delete(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().delete(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return true;
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }

    public boolean insert(T instance) {
        try {
                if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().save(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return true;
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }   

    public T find(Object primarykey) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            return (T) sessionFactory.getCurrentSession().get(entityClass,
                    (Integer) primarykey);
        } catch (RuntimeException re) {
            return null;
        }
    }
}
