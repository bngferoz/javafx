create table rollsix.temp (col1 char(5), col2 char(5))


create table rollsix.user(
   user_id INT NOT NULL AUTO_INCREMENT,
   email VARCHAR(100) NOT NULL,
   first_name VARCHAR(100) NOT NULL,
   last_name VARCHAR(100) NOT NULL,
   address VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL,
   role VARCHAR(50) NOT NULL,
   PRIMARY KEY ( user_id )
);


create table rollsix.product(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(100) NOT NULL,
   price decimal(18, 3) NOT NULL,
   quantity decimal(18, 3) NOT NULL,
   PRIMARY KEY ( id )
);