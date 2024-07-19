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
        List<User> users = userDAO.weeklyEmailSendList();
        for(User user : users) {
            String email = user.getEmail();
            String username = user.getUsername();
            String subject = "Your Weekly TravelTrax Update!";
            String message = "<html>"
                    + "<body>"
                    + "<p>Dear " + username + ",</p>"
                    + "<p>Happy Monday! We hope you had a fantastic weekend and are ready for an exciting new week. Here's your weekly update from TravelTrax:</p>"
                    + "<h3>🗺️ Discover New Destinations</h3>"
                    + "<p>This week, we're shining the spotlight on some hidden gems that you might want to add to your travel bucket list. Whether you're dreaming of serene beaches, bustling cities, or tranquil mountain retreats, we've got something for everyone.</p>"
                    + "<h3>📅 Plan Your Perfect Itinerary</h3>"
                    + "<p>Planning a trip can be overwhelming, but it doesn't have to be! Our expert tips and tools will help you create a seamless itinerary, so you can make the most of your travels without any stress.</p>"
                    + "<h3>🌍 Travel Smarter with Our Tips</h3>"
                    + "<p>From packing hacks to budget-friendly travel strategies, our weekly tips are designed to help you travel smarter and more efficiently. Don't miss out on our latest advice!</p>"
                    + "<h3>✈️ Exclusive Deals and Offers</h3>"
                    + "<p>As a valued TravelTrax user, you get access to exclusive travel deals and offers. Be sure to check out our latest discounts and save big on your next adventure.</p>"
                    + "<h3>📝 Share Your Stories</h3>"
                    + "<p>We love hearing from our community! Share your travel stories and photos with us, and you might be featured in our next newsletter. Inspire others with your incredible journeys.</p>"
                    + "<p>Thank you for being a part of the TravelTrax family. We look forward to helping you create unforgettable travel experiences.</p>"
                    + "<p>Safe travels!</p>"
                    + "<p>Best regards,</p>"
                    + "<p>The TravelTrax Team</p>"
                    + "</body>"
                    + "</html>";

            emailService.sendMail(email, subject, message);
        }
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendDailyAt9AM(){
        List<User> users = travelPlanDAO.sendMessageUser();
        for(User user : users){
            String email = user.getEmail();
            String username = user.getUsername();
            String subject = "Get Ready for Your Trip Tomorrow!";
            String message = "<html><body>" +
                    "<p>Dear " +username +" </p>" +
                    "<p>We hope you're as excited as we are about your upcoming trip! Here are some important details and tips to help you prepare:</p>" +
                    "<h3>Things to Remember</h3>" +
                    "<ul>" +
                    "<li><strong>Documents:</strong> Please ensure you have all necessary travel documents, including your passport, tickets, and any required visas.</li>" +
                    "<li><strong>Packing Tips:</strong> Pack according to the weather forecast and your planned activities. Don't forget essentials like chargers, toiletries, and any medications you might need.</li>" +
                    "<li><strong>Health & Safety:</strong> Check if there are any health advisories or safety tips for your destination. It’s always good to carry a basic first aid kit.</li>" +
                    "</ul>" +
                    "<h3>Final Checklist</h3>" +
                    "<ol>" +
                    "<li>Confirm your travel arrangements and accommodations.</li>" +
                    "<li>Notify your bank of your travel plans to avoid any issues with your cards.</li>" +
                    "<li>Set up an out-of-office message if needed.</li>" +
                    "<li>Double-check your packing list and ensure you have everything you need.</li>" +
                    "</ol>" +
                    "<p>We wish you a safe and enjoyable journey! If you have any last-minute questions, please do not hesitate to contact us.</p>" +
                    "<p>Best regards,<br>TravelTrax<br></p>" +
                    "<p>Happy Trip :)</p>" +
                    "</body></html>";

            emailService.sendMail(email, subject, message);
        }
    }

}
