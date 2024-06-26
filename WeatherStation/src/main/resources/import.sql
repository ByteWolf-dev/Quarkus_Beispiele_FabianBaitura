-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Insert data into Sensor table
INSERT INTO Sensor (name, location) VALUES ('Sensor1', 'Location1');
INSERT INTO Sensor (name, location) VALUES ('Sensor2', 'Location2');

-- Insert data into Measurement table
INSERT INTO Measurement (temperature, humidity, pressure, sensor_id) VALUES (25.5, 60.0, 1013.25, 1);
INSERT INTO Measurement (temperature, humidity, pressure, sensor_id) VALUES (22.0, 55.0, 1015.0, 1);
INSERT INTO Measurement (temperature, humidity, pressure, sensor_id) VALUES (27.5, 62.0, 1010.0, 2);
