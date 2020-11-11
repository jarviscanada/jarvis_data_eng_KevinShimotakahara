-- Show table schema 
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
SELECT COUNT(*) FROM retail;

-- number of clients (e.g. unique client ID)
SELECT COUNT( DISTINCT customer_id) as num_customers FROM retail;

-- max and min invoice dates
SELECT MAX(invoice_date), MAX(invoice_date) FROM retail;

-- num SKU merchants???
SELECT COUNT(DISTINCT stock_code) FROM retail;

-- avg invoice amount
SELECT Avg(invoice_price) 
FROM   (SELECT Sum(unit_price * quantity) AS invoice_price 
        FROM   retail 
        GROUP  BY invoice_no 
        HAVING Sum(unit_price * quantity) > 0) AS alias;

-- total revenue
SELECT Sum(unit_price * quantity) AS total_revenue 
FROM   retail 
HAVING Sum(unit_price * quantity) > 0;

-- total revenue by YYYYMM
select yyyymm, sum(unit_price*quantity) from
retail.sql (select extract(year from invoice_date)::VARCHAR || extract(month from invoice_date)::VARCHAR as YYYYMM, unit_price, quantity
retail.sql from retail) as alias
retail.sql group by yyyymm
retail.sql having(sum(unit_price*quantity)) > 0
retail.sql order by yyyymm;

