package com.huyvt.daoimp;

import com.huyvt.dao.ProductDAO;
import com.huyvt.dto.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository("productDAO")
@Transactional
public class ProductDAOImp implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product get(int productId) {
        try{
            return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List list() {
        return sessionFactory.getCurrentSession().createQuery("FROM Product").getResultList();
    }

    @Override
    public boolean add(Product product) {
        try {
            product.setActive(true);
            sessionFactory.getCurrentSession().persist(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {
            sessionFactory.getCurrentSession().update(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try {
            product.setActive(false);
            return this.update(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> listActiveProducts() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where active = :active");
        query.setParameter("active", true);
        return query.list();
    }

    @Override
    public List<Product> listActiveProductsByCategory(int categoryId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where active = :active and categoryId = :categoryId");
        query.setParameter("active", true);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    @Override
    public List<Product> getLastestActiveProducts(int count) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where active = :active order by id");
        query.setParameter("active", true);
        query.setFirstResult(0);
        query.setMaxResults(count);
        return query.list();
    }
}
