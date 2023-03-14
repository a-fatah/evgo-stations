-- liquibase formatted sql

-- changeset abdul:add-connector-data

INSERT INTO connector (id, connector_type, power, price, status) VALUES (1, 'TYPE_1', 5, 10, 'AVAILABLE');
INSERT INTO connector (id, connector_type, power, price, status) VALUES (2, 'TYPE_2', 10, 15, 'AVAILABLE');
INSERT INTO connector (id, connector_type, power, price, status) VALUES (3, 'CCS', 50, 20, 'AVAILABLE');
INSERT INTO connector (id, connector_type, power, price, status) VALUES (4, 'DC_FAST', 150, 30, 'AVAILABLE');

-- changeset abdul:add-stations-data

INSERT INTO station (name, location, amenities, opening_hours, rating) VALUES
       ('Charging Station 1', 'Alexanderplatz 1, Berlin', 'Restrooms, Wi-Fi', '24/7', 4.3),
       ('Charging Station 2', 'Kurfürstendamm 101, Berlin', 'Café, Parking', 'Mon-Fri: 8am-6pm, Sat: 10am-4pm', 4.8),
       ('Charging Station 3', 'Friedrichstraße 43-45, Berlin', 'Fast food, Lounge', '24/7', 3.9),
       ('Charging Station 4', 'Potsdamer Platz 1, Berlin', 'Restaurants, Shops', 'Mon-Sat: 10am-8pm, Sun: 12pm-6pm', 4.2),
       ('Charging Station 5', 'Leipziger Platz 12, Berlin', 'Shopping, Gym', 'Mon-Fri: 7am-10pm, Sat-Sun: 9am-9pm', 4.5);
