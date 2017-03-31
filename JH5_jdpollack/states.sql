CREATE TABLE states (StateName VARCHAR(32), Region ENUM('east', 'midwest', 'west', 'south', 'southwest'), LargestCity VARCHAR(32), Capital VARCHAR(32), Population int);
INSERT INTO states VALUES('Michigan', 'midwest', 'Detroit', 'Lansing', 9910000);
INSERT INTO states VALUES('California', 'west', 'Los Angeles', 'Sacramento', 38800000);
INSERT INTO states VALUES('New York', 'east', 'New York', 'Albany', 19750000);
INSERT INTO states VALUES('Washington ', 'west', 'Seattle', 'Olympia', 7062000);