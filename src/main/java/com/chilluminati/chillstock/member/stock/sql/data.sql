use chillstockDB;

-- user_table
INSERT INTO user_table (user_id, user_login_id, user_email, user_name, user_password, user_phone, user_approved_at, user_type, user_status, user_requested_at) VALUES
(1, 'user01', 'user01@email.com', '홍길동', 'pass01', '010-1111-1111', NOW(), 'MEMBER', 'APPROVED', NOW()),
(2, 'user02', 'user02@email.com', '김철수', 'pass02', '010-2222-2222', NOW(), 'MEMBER', 'APPROVED', NOW()),
(3, 'user03', 'user03@email.com', '이영희', 'pass03', '010-3333-3333', NOW(), 'MEMBER', 'PENDING', NOW()),
(4, 'admin01', 'admin01@email.com', '관리자1', 'adminpass1', '010-4444-4444', NOW(), 'ADMIN', 'APPROVED', NOW()),
(5, 'admin02', 'admin02@email.com', '관리자2', 'adminpass2', '010-5555-5555', NOW(), 'ADMIN', 'APPROVED', NOW());

-- admin_table (user_id 4, 5 are admins)
INSERT INTO admin_table (admin_id, admin_hire_date, admin_position, user_id) VALUES
(1, '2023-01-01', '매니저', 4),
(2, '2023-02-01', '팀장', 5);

-- business_table (linked to user_id 1~3)
INSERT INTO business_table (business_id, business_regist_num, business_name, business_address, user_id) VALUES
(1, '111-11-11111', '김밥천국', '서울 강남구', 1),
(2, '222-22-22222', '떡볶이나라', '서울 마포구', 2),
(3, '333-33-33333', '버거존', '서울 송파구', 3);

-- category_main
INSERT INTO category_main (category_id, category_name) VALUES
(1, '식품'), (2, '음료'), (3, '생활용품');

-- c_mid_level (linked to category_main)
INSERT INTO c_mid_level (category_mid_id, category_name, category_main_id) VALUES
(1, '냉동식품', 1),
(2, '탄산음료', 2),
(3, '세제', 3);

-- product (linked to c_mid_level + business)
INSERT INTO product (product_id, product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id) VALUES
(1, 500, '피자', 1, -18, '2025-12-31', 1),
(2, 300, '콜라', 2, 5, '2025-11-30', 1),
(3, 1000, '세탁세제', 3, 20, '2026-01-01', 2);

-- warehouse_table
INSERT INTO warehouse_table (warehouse_id, warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES
(1, 'A창고', 1000, '서울 성동구', 500);

-- storage_condition
INSERT INTO storage_condition (storage_id, storage_name, min_temp, max_temp, description) VALUES
(1, 'Frozen_Storage', -20, -10, '냉동 보관');

-- area_table (linked to warehouse and storage_condition)
INSERT INTO area_table (area_id, area_space, area_code, area_price, warehouse_id, storage_id) VALUES
(1, 100, 'A01', 10000, 1, 1);

-- inbound_table (linked to admin, product)
INSERT INTO inbound_table (inbound_id, inbound_date, inbound_request_date, inbound_status, admin_id, inbound_amount, product_id)
VALUES
(1, NOW(), NOW(), '승인', 1, 100, 1),
(2, NOW(), NOW(), '승인', 2, 150, 2),
(3, NOW(), NOW(), '승인', 1, 200, 3);

-- outbound_table (linked to admin, product)
INSERT INTO outbound_table (outbound_id, outbound_date, outbound_request_date, outbound_status, admin_id, outbound_amount, product_id)
VALUES
(1, NOW(), NOW(), '승인', 1, 30, 1),
(2, NOW(), NOW(), '승인', 2, 50, 2);

-- stock_table (linked to product + area)
INSERT INTO stock_table (stock_id, stock_amount, product_id, area_id) VALUES
(1, 70, 1, 1),
(2, 100, 2, 1),
(3, 200, 3, 1);

-- user_backup_table
INSERT INTO user_backup_table (user_login_id, user_email, user_name, user_phone, deleted_at, approved_at, requested_at) VALUES
('old_user1', 'old1@email.com', '박지훈', '010-7777-7777', NOW(), NOW(), NOW());
