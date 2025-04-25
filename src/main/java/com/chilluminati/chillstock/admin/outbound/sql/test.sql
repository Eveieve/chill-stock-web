use chillstockDB;

select * from outbound_table;

select o.outbound_id, o.outbound_date, o.outbound_request_date, o.outbound_status, o.outbound_amount, p.product_id, p.product_name, o.reject_reason_message
from outbound_table o
         join product p on o.product_id = p.product_id
         join business_table b on b.business_id = p.business_id
where b.user_id = 3;

select distinct s.stock_id, p.product_id, p.product_name, p.expiration_date, s.stock_amount, i.inbound_date
from product p
         join stock_table s on p.product_id = s.product_id
         join inbound_table i on i.product_id = p.product_id
         join business_table b on p.business_id = b.business_id
where b.user_id = 3
order by s.stock_id;


-- 출고 상태별 출고 요청
select * from outbound_table where outbound_status = '승인';

select outbound_id, outbound_date, outbound_request_date, outbound_status, outbound_amount, product_name, reject_reason_message
from outbound_table o
         join product p on o.product_id = p.product_id
where outbound_status = '취소';


-- 전체 출고 요청
select * from outbound_table;

-- 출고 요청 업데이트
-- user_id = 3, 출고 id 11, 출고 수량 5 / 재고수량 40
update outbound_table set outbound_status = '승인', admin_id = 1, outbound_date = now() where outbound_id = 11;

-- 출고 요청 승인, 반려
UPDATE stock_table s
    JOIN product p ON s.product_id = p.product_id
    JOIN outbound_table o ON o.product_id = p.product_id
    SET s.stock_amount = s.stock_amount - o.outbound_amount
WHERE o.outbound_status = '승인'
    AND o.outbound_id = 11
    AND s.stock_amount >= o.outbound_amount;

select * from stock_history_table;

-- 0인 재고 삭제
delete from stock_table where stock_amount = 0;

-- 출고 수량 재고 수량 가져오기
SELECT distinct s.stock_amount, o.outbound_amount
FROM stock_table s
    JOIN product p ON s.product_id = p.product_id
    JOIN outbound_table o ON o.product_id = p.product_id
WHERE o.outbound_id = 11;

-- admin_id 가져오기
select admin_id
from admin_table
where user_id = 1;