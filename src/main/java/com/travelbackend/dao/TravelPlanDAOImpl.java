package com.travelbackend.dao;

import com.travelbackend.entity.TravelPlan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TravelPlanDAOImpl implements TravelPlanDAO{

    private EntityManager entityManager;

    @Autowired
    public TravelPlanDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public TravelPlan save(TravelPlan travelPlan) {
        entityManager.persist(travelPlan);
        return travelPlan;
    }

    @Override
    public TravelPlan findTravelPlanById(int travelPlanId) {
        return entityManager.find(TravelPlan.class,travelPlanId);
    }

    @Override
    public List<TravelPlan> findAll() {
        TypedQuery<TravelPlan> query = entityManager.createQuery("from TravelPlan ",TravelPlan.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(TravelPlan travelPlan) {
        entityManager.merge(travelPlan);
    }

    @Override
    @Transactional
    public void delete(int travelPlanId) {
        TravelPlan travelPlan = entityManager.find(TravelPlan.class,travelPlanId);
        entityManager.remove(travelPlan);
    }

    @Override
    public List<String> sendMessageUser() {
        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT t.user.email FROM TravelPlan t WHERE t.startDate = :startDate",
                String.class
        );
        query.setParameter("startDate", threeDaysAgo);

        return query.getResultList();
    }
}
