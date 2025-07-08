-- Create audit tables

CREATE TABLE user_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_type VARCHAR(10),
    event_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    old_data JSON,
    new_data JSON
);

CREATE TABLE user_login_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    email VARCHAR(255),
    login_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE reservation_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_type VARCHAR(10),
    event_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    old_data JSON,
    new_data JSON
);

CREATE TABLE email_scheduler_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    email VARCHAR(255),
    subject VARCHAR(255),
    message TEXT,
    sent_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE frankfurter_api_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_currency VARCHAR(10),
    call_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE weather_api_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255),
    call_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE error_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    error_type VARCHAR(255),
    error_message TEXT,
    error_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Triggers for user entity
DELIMITER $$
CREATE TRIGGER user_after_insert
AFTER INSERT ON users FOR EACH ROW
BEGIN
    INSERT INTO user_audit (event_type, new_data)
    VALUES ('INSERT', JSON_OBJECT(
        'id', NEW.id,
        'email', NEW.email,
        'firstName', NEW.first_name,
        'lastName', NEW.last_name,
        'role', NEW.role
    ));
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER user_after_update
AFTER UPDATE ON users FOR EACH ROW
BEGIN
    INSERT INTO user_audit (event_type, old_data, new_data)
    VALUES ('UPDATE',
        JSON_OBJECT(
            'id', OLD.id,
            'email', OLD.email,
            'firstName', OLD.first_name,
            'lastName', OLD.last_name,
            'role', OLD.role
        ),
        JSON_OBJECT(
            'id', NEW.id,
            'email', NEW.email,
            'firstName', NEW.first_name,
            'lastName', NEW.last_name,
            'role', NEW.role
        )
    );
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER user_after_delete
AFTER DELETE ON users FOR EACH ROW
BEGIN
    INSERT INTO user_audit (event_type, old_data)
    VALUES ('DELETE', JSON_OBJECT(
        'id', OLD.id,
        'email', OLD.email,
        'firstName', OLD.first_name,
        'lastName', OLD.last_name,
        'role', OLD.role
    ));
END$$
DELIMITER ;


-- Triggers for reservation entity

DELIMITER $$
CREATE TRIGGER reservation_after_insert
AFTER INSERT ON reservation FOR EACH ROW
BEGIN
    INSERT INTO reservation_audit (event_type, new_data)
    VALUES ('INSERT', JSON_OBJECT(
        'id', NEW.id,
        'reservationDate', NEW.reservation_date,
        'status', NEW.status,
        'user_id', NEW.user_id,
        'trip_id', NEW.trip_id
    ));
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER reservation_after_update
AFTER UPDATE ON reservation FOR EACH ROW
BEGIN
    INSERT INTO reservation_audit (event_type, old_data, new_data)
    VALUES ('UPDATE',
        JSON_OBJECT(
            'id', OLD.id,
            'reservationDate', OLD.reservation_date,
            'status', OLD.status,
            'user_id', OLD.user_id,
            'trip_id', OLD.trip_id
        ),
        JSON_OBJECT(
            'id', NEW.id,
            'reservationDate', NEW.reservation_date,
            'status', NEW.status,
            'user_id', NEW.user_id,
            'trip_id', NEW.trip_id
        )
    );
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER reservation_after_delete
AFTER DELETE ON reservation FOR EACH ROW
BEGIN
    INSERT INTO reservation_audit (event_type, old_data)
    VALUES ('DELETE', JSON_OBJECT(
        'id', OLD.id,
        'reservationDate', OLD.reservation_date,
        'status', OLD.status,
        'user_id', OLD.user_id,
        'trip_id', OLD.trip_id
    ));
END$$
DELIMITER ;