#view 1
CREATE OR REPLACE VIEW customer_view AS
SELECT P_name,p_price,p_id
FROM Product;
SELECT * FROM CUSTOMER_VIEW;

#view view2
CREATE OR REPLACE VIEW supplier_view AS
SELECT P_name,p_available_quantity,p_id
FROM Product;
SELECT * FROM SUPPLIER_VIEW;