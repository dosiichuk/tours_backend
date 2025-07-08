
--  SET FOREIGN_KEY_CHECKS = 0;
--  TRUNCATE TABLE review;
--  TRUNCATE TABLE flight;
--  TRUNCATE TABLE reservation;
--  TRUNCATE TABLE hotel_trip;
--  TRUNCATE TABLE trip;
--  TRUNCATE TABLE hotel_amenity;
--  TRUNCATE TABLE hotel;
--  TRUNCATE TABLE location;
--  TRUNCATE TABLE weather;
--  TRUNCATE TABLE amenity;
--  TRUNCATE TABLE user;
--  SET FOREIGN_KEY_CHECKS = 1;

-- Users
INSERT INTO `users` (email, password, first_name, last_name, role) VALUES ('de99887711@gmail.com', 'adminpass', 'Admin', 'User', 'ADMIN');
INSERT INTO `users` (email, password, first_name, last_name, role) VALUES ('user@example.com', 'userpass', 'Regular', 'User', 'USER');
INSERT INTO `users` (email, password, first_name, last_name, role) VALUES ('manager@example.com', 'managerpass', 'Manager', 'Boss', 'USER');

-- Amenities
INSERT INTO amenity (name, description) VALUES ('WiFi', 'Free wireless internet');
INSERT INTO amenity (name, description) VALUES ('Pool', 'Outdoor swimming pool');
INSERT INTO amenity (name, description) VALUES ('Breakfast', 'Complimentary breakfast');

-- Weather
INSERT INTO weather (temperature, humidity) VALUES (22.5, 60.0);
INSERT INTO weather (temperature, humidity) VALUES (28.0, 50.0);
INSERT INTO weather (temperature, humidity) VALUES (15.0, 80.0);

-- Locations
INSERT INTO location (name, description, address, latitude, longitude, weather_id) VALUES ('Paris', 'City of Light', '123 Paris St', '48.8566', '2.3522', 1);
INSERT INTO location (name, description, address, latitude, longitude, weather_id) VALUES ('London', 'Capital of England', '456 London Rd', '51.5074', '-0.1278', 2);
INSERT INTO location (name, description, address, latitude, longitude, weather_id) VALUES ('Rome', 'Eternal City', '789 Rome Ave', '41.9028', '12.4964', 3);

-- Hotels
INSERT INTO hotel (name, address, phone_number, email, description, location_id) VALUES ('Hotel Paris', '123 Paris St', '123456789', 'paris@hotel.com', 'A nice hotel in Paris', 1);
INSERT INTO hotel (name, address, phone_number, email, description, location_id) VALUES ('London Inn', '456 London Rd', '987654321', 'london@hotel.com', 'A cozy hotel in London', 2);
INSERT INTO hotel (name, address, phone_number, email, description, location_id) VALUES ('Rome Suites', '789 Rome Ave', '555666777', 'rome@hotel.com', 'Luxury suites in Rome', 2);

-- Hotel-Amenity join table
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 1);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (1, 2);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 1);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (2, 3);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (3, 2);
INSERT INTO hotel_amenity (hotel_id, amenity_id) VALUES (3, 3);

-- Trips
INSERT INTO trip (title, description, price, start_date, end_date) VALUES ('Paris Getaway', 'A trip to Paris', 999.99, '2025-07-01', '2025-07-07');
INSERT INTO trip (title, description, price, start_date, end_date) VALUES ('London Adventure', 'Explore London', 799.99, '2025-08-10', '2025-08-17');
INSERT INTO trip (title, description, price, start_date, end_date) VALUES ('Rome Discovery', 'Discover Rome', 699.99, '2025-09-05', '2025-09-12');

-- Hotel-Trip join table
INSERT INTO hotel_trip (hotel_id, trip_id) VALUES (1, 1);
INSERT INTO hotel_trip (hotel_id, trip_id) VALUES (2, 2);
INSERT INTO hotel_trip (hotel_id, trip_id) VALUES (3, 3);

-- Reservations
INSERT INTO reservation (reservation_date, status, user_id, trip_id) VALUES ('2025-06-01', 'CONFIRMED', 2, 1);
INSERT INTO reservation (reservation_date, status, user_id, trip_id) VALUES ('2025-07-15', 'PENDING', 2, 2);
INSERT INTO reservation (reservation_date, status, user_id, trip_id) VALUES ('2025-08-20', 'CANCELLED', 1, 2);

-- Flights
INSERT INTO flight (flight_number, departure_airport, arrival_airport, departure_date, arrival_date, airline, reservation_id)
VALUES ('AF123', 'CDG', 'JFK', '2025-07-01', '2025-07-01', 'Air France', 1);
INSERT INTO flight (flight_number, departure_airport, arrival_airport, departure_date, arrival_date, airline, reservation_id)
VALUES ('BA456', 'LHR', 'JFK', '2025-08-10', '2025-08-10', 'British Airways', 2);
INSERT INTO flight (flight_number, departure_airport, arrival_airport, departure_date, arrival_date, airline, reservation_id)
VALUES ('AZ789', 'FCO', 'JFK', '2025-09-05', '2025-09-05', 'Alitalia', 3);

-- Reviews
INSERT INTO review (content, rating, user_id, trip_id) VALUES ('Great trip!', 5, 2, 1);
INSERT INTO review (content, rating, user_id, trip_id) VALUES ('Nice experience!', 4, 2, 2);
INSERT INTO review (content, rating, user_id, trip_id) VALUES ('Could be better.', 3, 1, 2);
