use chillstockDB;

desc user_tbl;
select * from user_tbl;

select * from user_table;

select * from product;

select b.business_id from business_table b join user_table u on b.user_id = u.user_id where b.user_id = 1;

select distinct s.stock_id, p.product_name, p.expiration_date, s.stock_amount, i.inbound_date
from product p
         join stock_table s on p.product_id = s.product_id
         join inbound_table i on i.product_id = p.product_id
         join business_table b on p.business_id = b.business_id
where b.business_id = (select b.business_id from business_table b join user_table u on b.user_id = u.user_id where u.user_id = 1) -- 로그인 한 사용자의 user_id
order by s.stock_id;

select distinct s.stock_id, p.product_id, p.product_name, p.expiration_date, s.stock_amount, i.inbound_date
from product p
         join stock_table s on p.product_id = s.product_id
         join inbound_table i on i.product_id = p.product_id
         join business_table b on p.business_id = b.business_id
where b.user_id = 1
order by s.stock_id;