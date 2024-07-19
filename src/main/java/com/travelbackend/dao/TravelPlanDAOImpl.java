package com.travelbackend.dao;

import com.travelbackend.dto.HotelVisitCountDTO;
import com.travelbackend.dto.TravelTypeDTO;
import com.travelbackend.dto.UserTravelCountDTO;
import com.travelbackend.entity.TravelPlan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public List<UserTravelCountDTO> countTravelsByUser() {
        String sql =
                "SELECT u.id, u.username, COUNT(u.username) AS total_travels " +
                        "FROM travel_plan t " +
                        "JOIN user u ON t.user_id = u.id " +
                        "GROUP BY u.username";
        Query query = entityManager.createNativeQuery(sql);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results.stream()
                .map(result -> new UserTravelCountDTO(
                        ((Number) result[0]).intValue(),
                        (String) result[1],
                        ((Number) result[2]).intValue()))
                .toList();
    }

    @Override
    public TravelTypeDTO getTravelByPercent() {
        String sql =
                "WITH revenue_types AS (" +
                        "    SELECT " +
                        "        SUM(CASE WHEN flight_class IS NOT NULL AND accommodation_id IS NULL AND bus_class IS NULL THEN total_price ELSE 0 END) AS flight_only_revenue, " +
                        "        SUM(CASE WHEN bus_class IS NOT NULL AND accommodation_id IS NULL AND flight_class IS NULL THEN total_price ELSE 0 END) AS bus_only_revenue, " +
                        "        SUM(CASE WHEN accommodation_id IS NOT NULL AND flight_class IS NULL AND bus_class IS NULL THEN total_price ELSE 0 END) AS hotel_only_revenue, " +
                        "        SUM(CASE WHEN accommodation_id IS NOT NULL AND flight_class IS NOT NULL AND bus_class IS NULL THEN total_price ELSE 0 END) AS flight_hotel_revenue, " +
                        "        SUM(CASE WHEN accommodation_id IS NOT NULL AND bus_class IS NOT NULL AND flight_class IS NULL THEN total_price ELSE 0 END) AS bus_hotel_revenue, " +
                        "        SUM(total_price) AS total_revenue " +
                        "    FROM travel_plan " +
                        "), " +
                        "percentage_distribution AS (" +
                        "    SELECT " +
                        "        flight_only_revenue / total_revenue * 100 AS flight_only_percentage, " +
                        "        bus_only_revenue / total_revenue * 100 AS bus_only_percentage, " +
                        "        hotel_only_revenue / total_revenue * 100 AS hotel_only_percentage, " +
                        "        flight_hotel_revenue / total_revenue * 100 AS flight_hotel_percentage, " +
                        "        bus_hotel_revenue / total_revenue * 100 AS bus_hotel_percentage " +
                        "    FROM revenue_types " +
                        ") " +
                        "SELECT * FROM percentage_distribution";

        Query query = entityManager.createNativeQuery(sql);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        if (results.isEmpty()) {
            return new TravelTypeDTO(0.0, 0.0, 0.0, 0.0, 0.0);
        }

        Object[] result = results.get(0);
        return new TravelTypeDTO(
                ((Number) result[0]).doubleValue(),
                ((Number) result[1]).doubleValue(),
                ((Number) result[2]).doubleValue(),
                ((Number) result[3]).doubleValue(),
                ((Number) result[4]).doubleValue()
        );
    }

    @Override
    public List<HotelVisitCountDTO> getTopHotelVisit() {
//        SELECT h.name, COUNT(h.name) as total_visit_hotels
//        FROM travel_plan t JOIN accommodation a ON t.accommodation_id = a.id JOIN room r ON a.room_id = r.id
//        JOIN hotel h ON r.hotel_id = h.id GROUP BY h.name ORDER BY total_visit_hotels DESC;
        String sql = "SELECT h.name, COUNT(h.name) as totalVisited " +
                "FROM travel_plan t " +
                "JOIN accommodation a ON t.accommodation_id = a.id " +
                "JOIN room r ON a.room_id = r.id " +
                "JOIN hotel h ON r.hotel_id = h.id " +
                "GROUP BY h.name " +
                "ORDER BY totalVisited";

        Query query = entityManager.createNativeQuery(sql);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        List<HotelVisitCountDTO> visitCountDTOS = new ArrayList<>();
        for (Object[] result : results) {
            String name = (String) result[0];
            Long totalVisited = ((Number) result[1]).longValue();
            visitCountDTOS.add(new HotelVisitCountDTO(name, totalVisited));
        }
        return visitCountDTOS;
    }

    @Override
    public List<Long> totalIncomeByMonth(int year) {
        String sql =
                "WITH all_months AS (" +
                        "    SELECT 1 AS month UNION " +
                        "    SELECT 2 UNION " +
                        "    SELECT 3 UNION " +
                        "    SELECT 4 UNION " +
                        "    SELECT 5 UNION " +
                        "    SELECT 6 UNION " +
                        "    SELECT 7 UNION " +
                        "    SELECT 8 UNION " +
                        "    SELECT 9 UNION " +
                        "    SELECT 10 UNION " +
                        "    SELECT 11 UNION " +
                        "    SELECT 12 " +
                        ") " +
                        "SELECT " +
                        "    all_months.month, " +
                        "    COALESCE(travel_counts.total_revenue, 0) AS total_revenue " +
                        "FROM " +
                        "    all_months " +
                        "LEFT JOIN ( " +
                        "    SELECT " +
                        "        MONTH(start_date) AS month, " +
                        "        SUM(total_price) AS total_revenue " +
                        "    FROM " +
                        "        travel_plan " +
                        "    WHERE " +
                        "        YEAR(start_date) = :year " +
                        "    GROUP BY " +
                        "        MONTH(start_date) " +
                        ") AS travel_counts ON all_months.month = travel_counts.month " +
                        "ORDER BY " +
                        "    all_months.month";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("year", year);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        List<Long> revenueList = new ArrayList<>();

        for (Object[] result : results) {
            revenueList.add(((Number) result[1]).longValue());
        }
        return revenueList;
    }

    @Override
    public List<Long> newMemberByMonth(int year) {
        String sql =
                "WITH all_months AS (" +
                        "    SELECT 1 AS month UNION ALL " +
                        "    SELECT 2 UNION ALL " +
                        "    SELECT 3 UNION ALL " +
                        "    SELECT 4 UNION ALL " +
                        "    SELECT 5 UNION ALL " +
                        "    SELECT 6 UNION ALL " +
                        "    SELECT 7 UNION ALL " +
                        "    SELECT 8 UNION ALL " +
                        "    SELECT 9 UNION ALL " +
                        "    SELECT 10 UNION ALL " +
                        "    SELECT 11 UNION ALL " +
                        "    SELECT 12 " +
                        "), " +
                        "user_counts AS (" +
                        "    SELECT " +
                        "        MONTH(created_at) AS month, " +
                        "        COUNT(*) AS user_count " +
                        "    FROM user " +
                        "    WHERE YEAR(created_at) = :year " +
                        "    AND role = 'USER' " +
                        "    GROUP BY MONTH(created_at) " +
                        ") " +
                        "SELECT " +
                        "    all_months.month, " +
                        "    COALESCE(user_counts.user_count, 0) AS user_count " +
                        "FROM all_months " +
                        "LEFT JOIN user_counts ON all_months.month = user_counts.month " +
                        "ORDER BY all_months.month";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("year", year);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        List<Long> memberCount = new ArrayList<>();

        for (Object[] result : results) {
            memberCount.add(((Number) result[1]).longValue());
        }
        return memberCount;
    }

    @Override
    public List<Long> getTotalTravelCountByMonth(int year) {
        String sql =
                "WITH all_months AS (" +
                        "    SELECT 1 AS month UNION ALL " +
                        "    SELECT 2 UNION ALL " +
                        "    SELECT 3 UNION ALL " +
                        "    SELECT 4 UNION ALL " +
                        "    SELECT 5 UNION ALL " +
                        "    SELECT 6 UNION ALL " +
                        "    SELECT 7 UNION ALL " +
                        "    SELECT 8 UNION ALL " +
                        "    SELECT 9 UNION ALL " +
                        "    SELECT 10 UNION ALL " +
                        "    SELECT 11 UNION ALL " +
                        "    SELECT 12 " +
                        "), " +
                        "travel_counts AS (" +
                        "    SELECT " +
                        "        MONTH(start_date) AS month, " +
                        "        COUNT(*) AS total_travel_count " +
                        "    FROM travel_plan " +
                        "    WHERE YEAR(start_date) = :year " +
                        "    GROUP BY MONTH(start_date) " +
                        ") " +
                        "SELECT " +
                        "    all_months.month, " +
                        "    COALESCE(travel_counts.total_travel_count, 0) AS total_travel_count " +
                        "FROM all_months " +
                        "LEFT JOIN travel_counts ON all_months.month = travel_counts.month " +
                        "ORDER BY all_months.month";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("year", year);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();
        List<Long> travelCount = new ArrayList<>();

        for (Object[] result : results) {
            travelCount.add(((Number) result[1]).longValue());
        }
        return travelCount;
    }
}
