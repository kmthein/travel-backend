package com.travelbackend.dao;

import com.travelbackend.entity.User;
import com.travelbackend.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUserById(int userId) {
        return entityManager.find(User.class,userId);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("from User",User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("from User u where u.email=:email and u.isDelete=false ", User.class);
        query.setParameter("email",email);
        try {
            return query.getSingleResult();
        } catch (NoResultException error){
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(int userId) {
        User user = entityManager.find(User.class,userId);
        entityManager.remove(user);
    }

    @Override
    public List<User> findByNormalUser() {
        TypedQuery<User> query = entityManager.createQuery("from User u where u.role='USER' and u.isDelete=false ", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> weeklyEmailSendList() {
        TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE u.emailReceive = true and u.role='USER' and u.isDelete=false ", User.class);
        return query.getResultList();
    }
}
