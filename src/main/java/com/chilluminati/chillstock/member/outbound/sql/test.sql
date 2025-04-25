use chillstockDB;

insert into outbound_table (outbound_amount, product_id) values (10, 1);

select * from outbound_table;
select * from user_table;
desc outbound_table;

select o.outbound_id, o.outbound_date, o.outbound_request_date, o.outbound_status, o.outbound_amount, p.product_id, p.product_name, o.reject_reason_code
from outbound_table o
    join product p on o.product_id = p.product_id
    join business_table b on b.business_id = p.business_id
where b.user_id = 3;
