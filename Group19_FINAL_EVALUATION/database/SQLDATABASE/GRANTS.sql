CREATE USER 'ADMIN'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON *.* to 'admin'@'localhost' WITH GRANT OPTION;
CREATE USER 'customer'@'localhost' IDENTIFIED BY 'password';
GRANT SELECT on project.product to 'customer'@'localhost';
GRANT ALL ON project.Orders to 'customer'@'localhost';
CREATE USER 'supplier'@'localhost' IDENTIFIED BY 'password';
GRANT UPDATE on project.product to 'customer'@'localhost';