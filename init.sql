CREATE TABLE Sensors
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(30) NOT NULL
);

CREATE TABLE Measurements
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sensor_id int REFERENCES Sensors(id) NOT NULL,
    value int NOT NULL CHECK ( value > -100 AND value < 100 ),
    raining boolean NOT NULL
);