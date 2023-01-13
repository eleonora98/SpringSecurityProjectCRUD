CREATE TABLE USERS ( 
id int NOT NULL AUTO_INCREMENT,
username varchar(100) NOT NULL UNIQUE, 
email varchar(100) NOT NULL UNIQUE, 
first_name varchar(100) NOT NULL, 
last_name varchar(100) NOT NULL, 
password varchar(64) NOT NULL, 
avatar varchar(200), 
PRIMARY KEY (id) 
);

CREATE TABLE ROLES ( 
id int NOT NULL AUTO_INCREMENT, 
name varchar(100) NOT NULL, 
PRIMARY KEY (id) 
);

CREATE TABLE USERS_ROLES ( 
user_id int NOT NULL, 
role_id int NOT NULL, 
FOREIGN KEY (user_id) REFERENCES users(id), 
FOREIGN KEY (role_id) REFERENCES roles(id) 
);

CREATE TABLE CATEGORIES (
id int NOT NULL AUTO_INCREMENT, 
name varchar(100),
PRIMARY KEY (id)
);

CREATE TABLE PRODUCTS ( 
id int NOT NULL AUTO_INCREMENT, 
name varchar(100) unique, 
description varchar(2000), 
price double(5,2),
quantity int,
category_id int,
FOREIGN KEY (category_id) REFERENCES categories (id),
PRIMARY KEY (id) 
);

CREATE TABLE COMMENTS ( 
id int NOT NULL AUTO_INCREMENT, 
user_id int NOT NULL, 
description varchar(2000) NOT NULL, 
date_uploaded date NOT NULL, 
FOREIGN KEY (user_id) REFERENCES users(id), 
PRIMARY KEY (id) 
);

INSERT INTO `users` (`username`, `email`, `first_name`, `last_name`, `password`) VALUES ( 'admin', 'admin@test.com', 'Eleonora', 'Peneva', PASSWORD('admin_'));