package com.tours.backend.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tours.backend.domain.User;
import com.tours.backend.domain.Mail;
import com.tours.backend.domain.Trip;
import com.tours.backend.services.EmailService;
import com.tours.backend.services.TripService;
import com.tours.backend.services.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HotTripsEmailScheduler {

    private static final String SUBJECT = "Hot Trips Alert";
    private final EmailService emailService;
    private final UserService userService;
    private final TripService tripService;

    // @Scheduled(fixedRate = 10000)
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendHotTripsEmail() {
        List<Trip> hotTrips = tripService.getTripsBelowASpecificPrice(700.0);
        if (hotTrips.isEmpty()) {
            return;
        }
        userService.getAllUsers().forEach(user -> {
            String message = getHotTripsMessageForUser(user, hotTrips);
            Mail mail = Mail.builder()
                    .mailTo(user.getEmail())
                    .subject(SUBJECT)
                    .message(message)
                    .toCc(null)
                    .build();
            emailService.sendEmail(mail);
        });
    }

    private String getHotTripsMessageForUser(User user, List<Trip> hotTrips) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello ").append(user.getFirstName()).append(",\n\n");
        sb.append("Check out these hot trips under $700:\n\n");

        for (Trip trip : hotTrips) {
            sb.append("- ").append(trip.getTitle())
                    .append(" (").append(trip.getPrice()).append(" USD)\n");
        }

        sb.append("\nBest regards,\nYour Travel Team");
        return sb.toString();
    }

}
