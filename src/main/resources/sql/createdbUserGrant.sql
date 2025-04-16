select now();

CREATE DATABASE chillstockDb;
show databases;
CREATE USER 'chillstock' IDENTIFIED BY 'chillstock1234';
GRANT ALL PRIVILEGES ON chillstockDb.* TO 'chillstock';
FLUSH PRIVILEGES;