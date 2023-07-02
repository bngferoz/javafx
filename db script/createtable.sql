create table rollsix.temp (col1 char(5), col2 char(5))


create table rollsix.user(
   user_id INT NOT NULL AUTO_INCREMENT,
   email VARCHAR(100) NOT NULL,
   first_name VARCHAR(100) NOT NULL,
   last_name VARCHAR(100) NOT NULL,
   address VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL,
   PRIMARY KEY ( user_id )
);