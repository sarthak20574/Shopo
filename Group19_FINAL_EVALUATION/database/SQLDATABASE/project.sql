drop table abcd;
DROP DATABASE IF EXISTS project ;
CREATE DATABASE project; 
USE project;
SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE Customer (
c_name varchar(50) NOT NULL,
c_phone BIGINT NOT NULL,    
c_email varchar(50), 
c_address  varchar(50) NOT NULL,
c_password varchar(20) NOT NULL,
c_DOB date ,
c_age int,
PRIMARY KEY (c_phone)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Product(
p_name varchar(40) NOT NULL,
p_id int(4) NOT NULL,
p_price int(4) NOT NULL,
p_available_quantity int(4) NOT NULL,
PRIMARY KEY (p_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Category(
cat_name varchar(20) NOT NULL,
PRIMARY KEY (cat_name)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Supplier(
s_name varchar(20) NOT NULL,
s_phone BIGint NOT NULL,
s_mail varchar(40),
s_address varchar(40),
s_password varchar(30),
PRIMARY KEY (s_phone)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Employees(	
e_id int NOT NULL,
e_status boolean,
phone BIGINT ,
email varchar(30),
hiring_date varchar(30),
e_name varchar(20) NOT NULL ,
e_password  varchar(20),
PRIMARY KEY (e_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Orders(
o_ID int NOT NULL,
total_cost int NOT NULL,
o_status  varchar(50),
date_added date,
p_quantity int,
p_id int,
foreign key(p_id) references product(p_ID),
PRIMARY KEY (o_ID)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Cart(
cart_ID int,
total_cost int,
PRIMARY KEY (cart_ID)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Bill(
invoice_ID int NOT NULL,
b_timestamp timestamp,
total_cost int,
PRIMARY KEY(invoice_ID)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE queries (	
e_id int,
c_phone BIGINT NOT NULL,   
query_id int(4) NOT NULL,
foreign key(c_phone) REFERENCES Customer(c_phone), 
foreign key(e_id) REFERENCES Employees(e_id), 
PRIMARY KEY (e_id,c_phone)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE have (
o_id int,
c_phone BIGINT NOT NULL ,
foreign key(c_phone) REFERENCES Customer(c_phone),    
foreign key(o_id) REFERENCES Orders(o_ID),
PRIMARY KEY (o_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE makes (
cart_id int,
c_phone BIGINT NOT NULL,
foreign key(c_phone) REFERENCES Customer(c_phone), 
foreign key(cart_id) REFERENCES Cart(cart_ID),    
PRIMARY KEY (cart_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE categorising(	
p_id int,
cat_name varchar(20) NOT NULL,
foreign key(cat_name) REFERENCES Category(cat_name), 
foreign key(p_id) REFERENCES Product(p_id),    
PRIMARY KEY (p_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE consists_of (
p_id int,
quantity int,
date_added  date,
cart_ID int,
foreign key(p_id) REFERENCES Product(p_id),
foreign key(cart_ID) REFERENCES Cart(cart_ID),
PRIMARY KEY (p_id,cart_ID)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE owns (
c_phone BIGINT NOT NULL,
invoice_id int,
o_count int,
foreign key(c_phone) REFERENCES Customer(c_phone),
foreign key(invoice_id) REFERENCES Bill(invoice_ID),
PRIMARY KEY (invoice_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE supplies (	
s_phone bigint,
p_id int,
p_quantity int,
foreign key(s_phone) REFERENCES Supplier(s_phone),
foreign key(p_id) REFERENCES Product(p_id),
PRIMARY KEY (p_id,s_phone)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE payment(
invoice_id int,
payment_mode varchar(50),
o_id int,
foreign key(invoice_id) REFERENCES Bill(invoice_ID),
foreign key(o_id) REFERENCES Orders(o_ID),
PRIMARY KEY (invoice_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;






