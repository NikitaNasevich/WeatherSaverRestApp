CREATE TABLE Sensors
(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(30) NOT NULL unique
);

CREATE TABLE Measurements
(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    value decimal NOT NULL CHECK ( value > -100 AND value < 100 ),
    raining boolean NOT NULL,
    added_at timestamp NOT NULL,
    sensor varchar(30) REFERENCES Sensors(name)
);

