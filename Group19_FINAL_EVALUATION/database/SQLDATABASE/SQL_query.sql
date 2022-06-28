#Display the details of all the products of category electronics whose price<1000.
Select * from product where product.p_price<10000 and product.p_id in
		(select p_id from categorising where cat_name= 'electronics');


#Display the details of customer who have some items in their cart. To notify and remind them that their cart have something
Select * from customer where c_phone in
	(select c_phone from makes where cart_id in
 (select cart_id from consists_of where quantity>0)) ;


#Display the order_id and customer-phone of the order which are delivered successfully.
(select c_phone, o_id from have where o_id in
		(Select o_id from orders where o_status= 'Delivered'));


#Display the details of the customers who have ordered more than 10 times.
Select * from customer where c_phone in
	(select c_phone from owns where o_count>10);

#customers who never queried ever    
SELECT C.c_Name, C.c_email
 from Customer C
Where c_phone NOT IN(
SELECT C.c_phone
from Customer C, queries Q
WHERE C.c_Phone=Q.C_Phone
);


#supply produxts with price>30
Select s_phone 
From Supplies
Where s_phone in(
Select S.s_phone
 From supplies S, Product P
Where P.p_price>30 and S.P_id =P.P_id
);



#Fetch all products who are having the same  price as product with id=10.
SELECT P2.p_id, P2.p_price FROM
PRODUCT P1, PRODUCT P2
WHERE P1.p_id= 10 ;



#suppliers of products ie out of stock
SELECT S_phone FROM supplies 
WHERE p_id in(
SELECT P_id FROM product 
WHERE p_available_quantity <50
);

#PRODUCT WHICH HAVE PRICE MORE THAN 1000 AND AVAILABLE QUANTITY >10
SELECT * FROM project.Product WHERE p_price > 1000 AND p_available_quantity  > 10;



#SELECT THE CUSTOMERS WHO HAVE PAID ONLINE
select * from customer where c_phone in
(select c_phone from have where o_id in
(select o_id from payment where payment_mode='online'));


#select the specific cateogory then show the products in it
SELECT * FROM Product WHERE p_id IN(
            SELECT P_id from categorising where cat_name = 'toys'
    );






 
    

