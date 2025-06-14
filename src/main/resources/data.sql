INSERT INTO role (role) VALUES ('ADMIN');
INSERT INTO role (role) VALUES ('USER');

INSERT INTO user (id, email, password, first_name, last_name, role_id) VALUES (1, 'admin@example.com', 'adminpass', 'Admin', 'User', 1);
INSERT INTO user (id, email, password, first_name, last_name, role_id) VALUES (2, 'user@example.com', 'userpass', 'Regular', 'User', 2);
ALTER TABLE user AUTO_INCREMENT = (SELECT MAX(id) + 1 FROM user);

INSERT INTO amenity (id, name, description) VALUES (1, 'WiFi', 'Free wireless internet');
INSERT INTO amenity (id, name, description) VALUES (2, 'Pool', 'Outdoor swimming pool');

INSERT INTO weather (id, temperature, humidity) VALUES (1, 22.5, 60.0);

INSERT INTO location (id, name, description, address, latitude, longitude, weather_id) VALUES (1, 'Paris', 'City of Light', '123 Paris St', '48.8566', '2.3522', 1);

INSERT INTO hotel (id, name, address, phone_number, email, description, location_id) VALUES (1, 'Hotel Paris', '123 Paris St', '123456789', 'paris@hotel.com', 'A nice hotel in Paris', 1);

INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 1);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 2);

INSERT INTO trip (id, title, description, price, start_date, end_date) VALUES (1, 'Paris Getaway', 'A trip to Paris', 999.99, '2025-07-01', '2025-07-07');

INSERT INTO hotel_trip (hotel_id, trip_id) VALUES (1, 1);

INSERT INTO reservation (id, reservation_date, status, user_id, trip_id) VALUES (1, '2025-06-01', 'CONFIRMED', 2, 1);

INSERT INTO flight (id, flight_number, departure_airport, arrival_airport, departure_date, arrival_date, airline, reservation_id)
VALUES (1, 'AF123', 'CDG', 'JFK', '2025-07-01', '2025-07-01', 'Air France', 1);

INSERT INTO review (id, content, rating, user_id, trip_id) VALUES (1, 'Great trip!', 5, 2, 1);
INSERT INTO review (id, content, rating, user_id, trip_id) VALUES (1, 'Nice trip!', 5, 2, 1);