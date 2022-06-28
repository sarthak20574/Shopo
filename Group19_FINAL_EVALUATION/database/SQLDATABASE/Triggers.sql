drop table abcd;
drop table customercount;
DROP TRIGGER ph_gap; 
DROP TRIGGER ph_gap1;

create table customercount(
count int 
);
insert into customercount(count) 
select sum(0);

#Trigger 1
CREATE TRIGGER ph_gap 
after insert on customer
for each row
update customercount
SET count=count+1;

#Trigger 2
CREATE TRIGGER ph_gap1  
after delete on customer
for each row
update customercount
SET count=count-1;

INSERT INTO Customer VALUES ('shobhit',1234567890,'a@gmail.com','djhf ijhfgwe iuegfg','fgiuh','1986-04-07');
INSERT INTO Customer VALUES ('shobhit1',1234567891,'a@gmail.com','djhf ijhf454we iuegfg','dsjsuhf','2001-09-10');
INSERT INTO Customer VALUES ('shobhit2',1234567892,'a@gmail.com','djhf ij78454we iuegfg','kjhgb','2002-09-10'); 
INSERT INTO Customer VALUES ('shobhit2',1234578892,'a@gmail.com','djhf ij78454we iuegfg','kjhgb','2002-09-10');

select * from customercount; 

DELETE FROM Customer WHERE c_phone=1234567890;
DELETE FROM Customer WHERE c_phone=1234567891;
DELETE FROM Customer WHERE c_phone=1234567892;
DELETE FROM Customer WHERE c_phone=1234578892;

select * from customercount;



#TRIGGER 3
DROP TRIGGER QUANTITYUPDATE; 
CREATE TRIGGER  QuantityUpdate 
AFTER INSERT ON Orders FOR EACH ROW 
UPDATE product
	SET p_available_quantity = p_available_quantity - NEW.p_quantity 
	WHERE  p_id=NEW.p_ID;
    
UPDATE  PRODUCT SET p_available_quantity = 34 where p_id=1;   
UPDATE  PRODUCT SET p_available_quantity = 43 where p_id=2;   
UPDATE  PRODUCT SET p_available_quantity = 40 where p_id=3;   
UPDATE  PRODUCT SET p_available_quantity = 22 where p_id=4; 
select * from product;
DELETE FROM ORDERS WHERE O_ID=9774;
DELETE FROM ORDERS WHERE O_ID=9754;
DELETE FROM ORDERS WHERE O_ID=1022;
DELETE FROM ORDERS WHERE O_ID=1078;  

INSERT INTO `orders`VALUES (9774,2456,'Dispatched','2021-07-16',20,1);
INSERT INTO `orders`VALUES (9754,15345,'Dispatched','2021-07-16',20,2);
INSERT INTO `orders`VALUES (1022,4545,'Dispatched','2021-07-16',10,3);
INSERT INTO `orders`VALUES (1078,4542,'Dispatched','2021-07-16',11,4);

select * from product;

#TRIGGER 4
DROP TRIGGER QuantityUpdate1;
CREATE TRIGGER  QuantityUpdate1
AFTER INSERT ON supplies FOR EACH ROW UPDATE product
SET p_available_quantity = p_available_quantity + NEW.p_quantity 
WHERE  p_id=NEW.p_id;
DELETE FROM supplies WHERE p_id=1; 
UPDATE  PRODUCT SET p_available_quantity = 50 where p_id=5;   
UPDATE  PRODUCT SET p_available_quantity = 60 where p_id=6;   
UPDATE  PRODUCT SET p_available_quantity = 70 where p_id=7;   
UPDATE  PRODUCT SET p_available_quantity = 80 where p_id=8; 

select * from product;
DELETE FROM supplies WHERE p_id=5;
DELETE FROM supplies WHERE p_id=6;
DELETE FROM supplies WHERE p_id=7;
DELETE FROM supplies WHERE p_id=8;

INSERT INTO  `supplies` VALUES (9317519976,5,100);
INSERT INTO  `supplies` VALUES (8503052892,6,100);
INSERT INTO  `supplies` VALUES (7675513907,7,100);
INSERT INTO  `supplies` VALUES (6581683191,8,100);
select * from product;
DELETE FROM ORDERS WHERE O_ID=9775;
DELETE FROM ORDERS WHERE O_ID=9755;
DELETE FROM ORDERS WHERE O_ID=1023;
DELETE FROM ORDERS WHERE O_ID=1079; 

INSERT INTO `orders`VALUES (9775,2456,'Dispatched','2021-07-16',20,5);
INSERT INTO `orders`VALUES (9755,15345,'Dispatched','2021-07-16',20,6);
INSERT INTO `orders`VALUES (1023,4545,'Dispatched','2021-07-16',10,7);
INSERT INTO `orders`VALUES (1079,4542,'Dispatched','2021-07-16',11,8);

select * from product;