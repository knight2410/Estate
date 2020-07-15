package com.huyvt.daoimp;

import com.huyvt.dao.CategoryDAO;
import com.huyvt.dto.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImp implements CategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List list() {
        // TODO Auto-generated method stub
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Category where active = :active");
        query.setParameter("active", true);
        return query.getResultList();
    }

    @Override
    public Category get(int id) {
        return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
    }

    @Override
    public boolean add(Category category) {
        try {
            //add category to db
            System.out.println(category.toString());
            sessionFactory.getCurrentSession().persist(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        try {
            //add category to db
            System.out.println(category.toString());
            sessionFactory.getCurrentSession().update(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Category category) {
        category.setActive(false);
        try {
            //add category to db
            System.out.println(category.toString());
            sessionFactory.getCurrentSession().update(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
