use chillstockDB;

select * from outbound_table;
select * from inbound_table;

-- 특정 사용자의 출고요청
select o.outbound_id, o.outbound_date, o.outbound_request_date, o.outbound_status, o.outbound_amount, p.product_name, o.reject_reason_code
from outbound_table o
         join product p on o.product_id = p.product_id
         join business_table b on b.business_id = p.business_id
where b.user_id = 3;

-- 특정 사용자의 입고요청
select i.inbound_id, i.inbound_date, i.inbound_request_date, i.inbound_status, i.inbound_amount, p.product_name, i.inbound_request_date
from inbound_table i
        join product p on i.product_id = p.product_id
        join business_table b on b.business_id = p.business_id
where b.user_id = 3;


