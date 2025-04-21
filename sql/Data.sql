-- ✅ 1. user_table
INSERT INTO user_table (user_login_id, user_email, user_name, user_password, user_phone, user_type, user_status, user_requested_at, user_approved_at)
VALUES
    ('admin001', 'admin1@example.com', '관리자홍', 'pw1', '010-0001-0001', 'ROLE_admin', 'approve', NOW(), NOW()),
    ('admin002', 'admin2@example.com', '관리자이', 'pw2', '010-0002-0002', 'ROLE_admin', 'approve', NOW(), NOW()),
    ('member001', 'mem1@example.com', '김상추', 'pw3', '010-0003-0003', 'ROLE_member', 'approve', NOW(), NOW()),
    ('member002', 'mem2@example.com', '이당근', 'pw4', '010-0004-0004', 'ROLE_member', 'approve', NOW(), NOW()),
    ('member003', 'mem3@example.com', '박마늘', 'pw5', '010-0005-0005', 'ROLE_member', 'approve', NOW(), NOW()),
    ('member004', 'mem4@example.com', '최고기', 'pw6', '010-0006-0006', 'ROLE_member', 'approve', NOW(), NOW()),
    ('member005', 'mem5@example.com', '장과일', 'pw7', '010-0007-0007', 'ROLE_member', 'approve', NOW(), NOW()),
    ('member006', 'mem6@example.com', '하해산', 'pw8', '010-0008-0008', 'ROLE_member', 'approve', NOW(), NOW());

-- ✅ 2. admin_table
INSERT INTO admin_table (admin_hire_date, admin_position, user_id)
VALUES
    (CURDATE(), '관리자', 1), (CURDATE(), '입고담당', 2),
    (CURDATE(), '출고담당', 3), (CURDATE(), '재고관리', 4),
    (CURDATE(), '품질담당', 5), (CURDATE(), '검수', 6),
    (CURDATE(), '배송담당', 7), (CURDATE(), '창고장', 8);

-- ✅ 3. business_table
INSERT INTO business_table (business_regist_num, business_name, business_address, user_id)
VALUES
    ('111-11-1111', '청정푸드', '서울시 A로', 3),
    ('222-22-2222', '햇살농장', '서울시 B로', 4),
    ('333-33-3333', '푸드컴퍼니', '서울시 C로', 5),
    ('444-44-4444', '해밀가든', '서울시 D로', 6),
    ('555-55-5555', '제철푸드', '서울시 E로', 7),
    ('666-66-6666', '반찬나라', '서울시 F로', 8),
    ('777-77-7777', '육해공유통', '서울시 G로', 3),
    ('888-88-8888', '자연마트', '서울시 H로', 4);

-- ✅ 4. category_main
INSERT INTO category_main (category_name)
VALUES ('채소'), ('육류'), ('해산물'), ('과일'), ('곡류'), ('유제품'), ('양념'), ('냉동식품');

-- ✅ 5. c_mid_level
INSERT INTO c_mid_level (category_name, category_main_id)
VALUES ('잎채소', 1), ('근채소', 1), ('소고기', 2), ('돼지고기', 2),
       ('생선', 3), ('갑각류', 3), ('열대과일', 4), ('국산과일', 4);

-- ✅ 6. storage_condition
INSERT INTO storage_condition (storage_name, min_temp, max_temp, description)
VALUES
    ('Ultra-Low_Temp', -80, -50, '극저온'),
    ('Frozen_Storage', -20, -10, '육류 보관'),
    ('Refrigerated_Storage', 0, 5, '채소/과일 보관'),
    ('Cool_Storage', 5, 15, '과일/음료'),
    ('Room_Temperature', 15, 25, '상온 보관'),
    ('Heated_Storage', 30, 50, '발효/보온');

-- ✅ 7. warehouse_table
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount)
VALUES
    ('서울창고', 1000, '서울 물류1', 0),
    ('부산창고', 1200, '부산 물류2', 0),
    ('대전창고', 900, '대전 물류3', 0),
    ('광주창고', 850, '광주 물류4', 0),
    ('인천창고', 1500, '인천 물류5', 0),
    ('대구창고', 1100, '대구 물류6', 0),
    ('전주창고', 950, '전주 물류7', 0),
    ('강릉창고', 800, '강릉 물류8', 0);

-- ✅ 8. area_table
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id)
VALUES
    (100, 'A-01', 10000, 1, 3), (120, 'A-02', 11000, 1, 2),
    (130, 'B-01', 12000, 2, 2), (90, 'B-02', 13000, 2, 3),
    (150, 'C-01', 14000, 3, 4), (110, 'C-02', 11000, 3, 5),
    (140, 'D-01', 12000, 4, 1), (100, 'D-02', 9000, 4, 6);

-- ✅ 9. product
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES
    (3, '상추', 1, 4, DATE_ADD(CURDATE(), INTERVAL 5 DAY), 1),
    (4, '당근', 2, 4, DATE_ADD(CURDATE(), INTERVAL 10 DAY), 2),
    (5, '소등심', 3, -15, DATE_ADD(CURDATE(), INTERVAL 20 DAY), 3),
    (6, '목살', 4, -18, DATE_ADD(CURDATE(), INTERVAL 25 DAY), 4),
    (2, '고등어', 5, -10, DATE_ADD(CURDATE(), INTERVAL 7 DAY), 5),
    (3, '새우살', 6, -12, DATE_ADD(CURDATE(), INTERVAL 14 DAY), 6),
    (2, '망고', 7, 10, DATE_ADD(CURDATE(), INTERVAL 4 DAY), 7),
    (3, '사과', 8, 5, DATE_ADD(CURDATE(), INTERVAL 10 DAY), 8);

-- ✅ 10. stock_table
INSERT INTO stock_table (stock_amount, product_id, area_id)
VALUES
    (100, 1, 1), (90, 2, 2), (80, 3, 3), (70, 4, 4),
    (60, 5, 5), (50, 6, 6), (40, 7, 7), (30, 8, 8);

-- ✅ 11. stock_history_table
INSERT INTO stock_history_table (stock_id, change_date, stock_quantity, change_type)
VALUES
    (1, NOW(), 100, '입고'), (2, NOW(), 90, '입고'),
    (3, NOW(), 80, '입고'), (4, NOW(), 70, '입고'),
    (5, NOW(), 60, '입고'), (6, NOW(), 50, '입고'),
    (7, NOW(), 40, '입고'), (8, NOW(), 30, '입고');

-- ✅ 12. inbound_table
INSERT INTO inbound_table (inbound_request_date, inbound_date, inbound_status, admin_id, inbound_amount, product_id, reject_reason_code)
VALUES
    (NOW(), NOW(), '승인', 1, 10, 1, NULL),
    (NOW(), NULL, '대기', 2, 15, 2, NULL),
    (NOW(), NOW(), '반려', 3, 20, 3, 'ERR01'),
    (NOW(), NOW(), '승인', 4, 25, 4, NULL),
    (NOW(), NULL, '대기', 5, 30, 5, NULL),
    (NOW(), NOW(), '반려', 6, 35, 6, 'ERR02'),
    (NOW(), NOW(), '승인', 7, 40, 7, NULL),
    (NOW(), NULL, '대기', 8, 45, 8, NULL);

-- ✅ 13. outbound_table
INSERT INTO outbound_table (outbound_request_date, outbound_date, outbound_status, admin_id, outbound_amount, product_id, reject_reason_code)
VALUES
    (NOW(), NOW(), '승인', 1, 5, 1, NULL),
    (NOW(), NULL, '대기', 2, 6, 2, NULL),
    (NOW(), NOW(), '취소', 3, 7, 3, 'ERR10'),
    (NOW(), NOW(), '승인', 4, 8, 4, NULL),
    (NOW(), NULL, '대기', 5, 9, 5, NULL),
    (NOW(), NOW(), '취소', 6, 10, 6, 'ERR11'),
    (NOW(), NOW(), '승인', 7, 11, 7, NULL),
    (NOW(), NULL, '대기', 8, 12, 8, NULL);

-- ✅ 14. user_backup_table
INSERT INTO user_backup_table (user_login_id, user_email, user_name, user_phone, deleted_at, approved_at, requested_at)
VALUES
    ('olduser1', 'old1@ex.com', '삭제유저1', '010-8888-0001', NOW(), NOW(), NOW()),
    ('olduser2', 'old2@ex.com', '삭제유저2', '010-8888-0002', NOW(), NOW(), NOW()),
    ('olduser3', 'old3@ex.com', '삭제유저3', '010-8888-0003', NOW(), NOW(), NOW()),
    ('olduser4', 'old4@ex.com', '삭제유저4', '010-8888-0004', NOW(), NOW(), NOW()),
    ('olduser5', 'old5@ex.com', '삭제유저5', '010-8888-0005', NOW(), NOW(), NOW()),
    ('olduser6', 'old6@ex.com', '삭제유저6', '010-8888-0006', NOW(), NOW(), NOW()),
    ('olduser7', 'old7@ex.com', '삭제유저7', '010-8888-0007', NOW(), NOW(), NOW()),
    ('olduser8', 'old8@ex.com', '삭제유저8', '010-8888-0008', NOW(), NOW(), NOW());
