-- Use the database
USE chillstockDb;

-- Storage Conditions
INSERT INTO storage_condition (storage_name, min_temp, max_temp, description) VALUES
('Ultra-Low_Temp', -80, -60, 'For ultra-low temperature storage requirements'),
('Frozen_Storage', -25, -15, 'For frozen goods storage'),
('Refrigerated_Storage', 2, 8, 'For refrigerated goods'),
('Cool_Storage', 8, 15, 'For temperature-sensitive items'),
('Room_Temperature', 15, 25, 'For ambient temperature storage'),
('Heated_Storage', 25, 35, 'For items requiring warm conditions');

-- Warehouses (5 warehouses in different locations)
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES
('Seoul Central', 10000, '서울특별시 강남구 테헤란로 123', 0),
('Busan Port', 15000, '부산광역시 남구 신선로 456', 0),
('Incheon Airport', 12000, '인천광역시 중구 공항로 789', 0),
('Daejeon Hub', 8000, '대전광역시 유성구 대학로 321', 0),
('Gwangju Center', 9000, '광주광역시 서구 상무중앙로 654', 0);

-- Areas in warehouses
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id)
SELECT 
    FLOOR(RAND() * (500 - 200 + 1) + 200) as area_space,
    CONCAT(
        CHAR(65 + FLOOR(RAND() * 4)),
        LPAD(FLOOR(RAND() * 99 + 1), 2, '0')
    ) as area_code,
    FLOOR(RAND() * (100000 - 50000 + 1) + 50000) as area_price,
    w.warehouse_id,
    FLOOR(RAND() * 6 + 1) as storage_id
FROM warehouse_table w
CROSS JOIN (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS numbers;

-- Users (Mix of admins and regular users)
INSERT INTO user_table (user_login_id, user_email, user_name, user_password, user_phone, user_approved_at, user_type, user_status, user_requested_at)
VALUES
-- Admin users
('admin1', 'admin1@chillstock.com', '김관리', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '010-1234-5678', NOW(), 'ADMIN', 'APPROVED', DATE_SUB(NOW(), INTERVAL 30 DAY)),
('admin2', 'admin2@chillstock.com', '이관리', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '010-2345-6789', NOW(), 'ADMIN', 'APPROVED', DATE_SUB(NOW(), INTERVAL 29 DAY)),
('admin3', 'admin3@chillstock.com', '박관리', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '010-3456-7890', NOW(), 'ADMIN', 'APPROVED', DATE_SUB(NOW(), INTERVAL 28 DAY));

-- Insert regular members
INSERT INTO user_table (user_login_id, user_email, user_name, user_password, user_phone, user_approved_at, user_type, user_status, user_requested_at)
SELECT 
    CONCAT('user', LPAD(num, 3, '0')),
    CONCAT('user', LPAD(num, 3, '0'), '@example.com'),
    CONCAT(
        ELT(1 + FLOOR(RAND() * 5), '김', '이', '박', '최', '정'),
        ELT(1 + FLOOR(RAND() * 10), '영수', '미영', '철수', '영희', '민수', '수진', '태호', '지영', '현우', '서연')
    ),
    '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2',
    CONCAT('010-', LPAD(FLOOR(RAND() * 9000 + 1000), 4, '0'), '-', LPAD(FLOOR(RAND() * 9000 + 1000), 4, '0')),
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY),
    'MEMBER',
    'APPROVED',
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY)
FROM (
    SELECT a.N + b.N * 10 + 1 as num
    FROM (SELECT 0 as N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
         (SELECT 0 as N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b
    ORDER BY num LIMIT 50
) numbers;

-- Admin details
INSERT INTO admin_table (admin_hire_date, admin_position, user_id)
SELECT 
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 1000) DAY),
    ELT(1 + FLOOR(RAND() * 3), '수석 관리자', '선임 관리자', '주임 관리자'),
    user_id
FROM user_table
WHERE user_type = 'ADMIN';

-- Business registrations
INSERT INTO business_table (business_regist_num, business_name, business_address, business_post, user_id)
SELECT 
    CONCAT(
        LPAD(FLOOR(RAND() * 999999999 + 1), 9, '0'),
        LPAD(FLOOR(RAND() * 99 + 1), 2, '0')
    ),
    CONCAT(
        ELT(1 + FLOOR(RAND() * 5), '대한', '한국', '서울', '부산', '글로벌'),
        ELT(1 + FLOOR(RAND() * 5), '식품', '물류', '유통', '상사', '무역'),
        ELT(1 + FLOOR(RAND() * 3), '주식회사', '(주)', '컴퍼니')
    ),
    CONCAT(
        ELT(1 + FLOOR(RAND() * 5), '서울시', '부산시', '인천시', '대전시', '광주시'),
        ELT(1 + FLOOR(RAND() * 5), ' 강남구', ' 해운대구', ' 연수구', ' 유성구', ' 서구'),
        ' 테헤란로 ',
        FLOOR(RAND() * 999 + 1),
        '번길 ',
        FLOOR(RAND() * 100 + 1)
    ),
    CONCAT(FLOOR(RAND() * 900 + 100), '-', LPAD(FLOOR(RAND() * 999 + 1), 3, '0')),
    user_id
FROM user_table
WHERE user_type = 'MEMBER'
LIMIT 30;

-- Categories (Main)
INSERT INTO category_main (category_name) VALUES
('식품'),
('음료'),
('의약품'),
('화장품'),
('전자제품');

-- Categories (Mid-level)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
-- 식품
('냉동식품', 1),
('신선식품', 1),
('가공식품', 1),
('유제품', 1),
-- 음료
('탄산음료', 2),
('주류', 2),
('생수', 2),
('과일음료', 2),
-- 의약품
('전문의약품', 3),
('일반의약품', 3),
('의료기기', 3),
-- 화장품
('기초화장품', 4),
('색조화장품', 4),
('향수', 4),
-- 전자제품
('가전제품', 5),
('모바일기기', 5),
('컴퓨터부품', 5);

-- Products
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id, created_at)
SELECT 
    FLOOR(RAND() * (1000 - 10 + 1) + 10) as product_size,
    CONCAT(
        ELT(1 + FLOOR(RAND() * 5), '프리미엄 ', '신선한 ', '오리지널 ', '특선 ', '홈메이드 '),
        CASE 
            WHEN c.category_main_id = 1 THEN ELT(1 + FLOOR(RAND() * 5), '치킨', '피자', '만두', '김치', '두부')
            WHEN c.category_main_id = 2 THEN ELT(1 + FLOOR(RAND() * 5), '콜라', '맥주', '주스', '물', '커피')
            WHEN c.category_main_id = 3 THEN ELT(1 + FLOOR(RAND() * 5), '항생제', '진통제', '영양제', '소화제', '파스')
            WHEN c.category_main_id = 4 THEN ELT(1 + FLOOR(RAND() * 5), '스킨', '로션', '크림', '마스크팩', '선크림')
            ELSE ELT(1 + FLOOR(RAND() * 5), '냉장고', '세탁기', '에어컨', 'TV', '청소기')
        END
    ) as product_name,
    c.category_mid_id,
    CASE 
        WHEN c.category_main_id IN (1, 2) THEN FLOOR(RAND() * 30 - 20)
        ELSE NULL
    END as storage_temperature,
    DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) as expiration_date,
    (SELECT business_id FROM business_table ORDER BY RAND() LIMIT 1) as business_id,
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 180) DAY) as created_at
FROM c_mid_level c
CROSS JOIN (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS numbers;

-- Stock
INSERT INTO stock_table (stock_amount, product_id, area_id)
SELECT 
    FLOOR(RAND() * (1000 - 50 + 1) + 50) as stock_amount,
    p.product_id,
    (SELECT area_id FROM area_table ORDER BY RAND() LIMIT 1) as area_id
FROM product p;

-- Inbound records
INSERT INTO inbound_table (inbound_date, inbound_request_date, inbound_status, admin_id, inbound_amount, product_id)
SELECT 
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 30) DAY) as inbound_date,
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 60) DAY) as inbound_request_date,
    ELT(1 + FLOOR(RAND() * 3), '승인', '반려', '대기') as inbound_status,
    (SELECT admin_id FROM admin_table ORDER BY RAND() LIMIT 1) as admin_id,
    FLOOR(RAND() * (500 - 50 + 1) + 50) as inbound_amount,
    product_id
FROM product
LIMIT 100;

-- Outbound records
INSERT INTO outbound_table (outbound_date, outbound_request_date, outbound_status, admin_id, outbound_amount, product_id)
SELECT 
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 30) DAY) as outbound_date,
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 60) DAY) as outbound_request_date,
    ELT(1 + FLOOR(RAND() * 3), '승인', '반려', '대기') as outbound_status,
    (SELECT admin_id FROM admin_table ORDER BY RAND() LIMIT 1) as admin_id,
    FLOOR(RAND() * (200 - 20 + 1) + 20) as outbound_amount,
    product_id
FROM product
LIMIT 100;

-- Update warehouse amounts based on stock
UPDATE warehouse_table w
SET warehouse_amount = (
    SELECT COALESCE(SUM(s.stock_amount), 0)
    FROM stock_table s
    JOIN area_table a ON s.area_id = a.area_id
    WHERE a.warehouse_id = w.warehouse_id
);



