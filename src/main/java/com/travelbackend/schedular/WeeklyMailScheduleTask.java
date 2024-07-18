package com.travelbackend.schedular;

import com.travelbackend.dao.TravelPlanDAO;
import com.travelbackend.dao.UserDAO;
import com.travelbackend.entity.User;
import com.travelbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeeklyMailScheduleTask {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TravelPlanDAO travelPlanDAO;


    @Scheduled(cron = "0 0 9 * * MON")
    public void sendWeeklyMessage() {
        List<String> users = userDAO.weeklyEmailSendList();
        emailService.sendMail(users, "Weekly Report", "hello world");
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendDailyAt9AM(){
        List<String> users = travelPlanDAO.sendMessageUser();
        emailService.sendMail(users, "test","test");
    }

}
