use chillstockdb;
-- ✅ 사용자: 관리자 & 사업체회원
INSERT INTO user_table (
    user_login_id, user_email, user_name, user_password, user_phone,
    user_approved_at, user_type, user_status, user_requested_at
) VALUES
      ('admin002', 'admin@wms.com', '홍관리', 'admin_pw', '010-1111-2222', NOW(), 'ROLE_admin', 'approve', NOW()),
      ('foodceo01', 'ceo@marketfarm.com', '김푸드', 'market_pw', '010-2222-3333', NOW(), 'ROLE_member', 'approve', NOW());

-- ✅ 관리자 등록
INSERT INTO admin_table (admin_hire_date, admin_position, user_id)
VALUES (DATE('2022-01-01'), '창고관리자', 1);

-- ✅ 사업체 등록
INSERT INTO business_table (business_regist_num, business_name, business_address, user_id)
VALUES ('123-45-67890', 'MarketFarm', '경기도 고양시 일산동구 123', 2);

-- ✅ 카테고리 대분류
INSERT INTO category_main (category_name)
VALUES ('채소류'), ('육류'), ('해산물');

-- ✅ 카테고리 중분류
INSERT INTO c_mid_level (category_name, category_main_id)
VALUES
    ('잎채소', 1),
    ('뿌리채소', 1),
    ('소고기', 2),
    ('돼지고기', 2),
    ('생선', 3),
    ('조개류', 3);

-- ✅ 제품 등록 (식재료)
INSERT INTO product (
    product_size, product_name, category_mid_id, storage_temperature,
    expiration_date, business_id
) VALUES
      (10, '상추', 1, 5, DATE('2025-05-01'), 1),
      (15, '당근', 2, 4, DATE('2025-06-15'), 1),
      (50, '한우등심', 3, -2, DATE('2025-04-30'), 1),
      (45, '삼겹살', 4, -5, DATE('2025-05-05'), 1),
      (30, '고등어', 5, -10, DATE('2025-04-28'), 1),
      (25, '홍합', 6, -8, DATE('2025-05-02'), 1);

-- ✅ 창고
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount)
VALUES ('일산저온창고', 2000, '경기도 고양시 저온로 45', 0);

-- ✅ 보관 조건
INSERT INTO storage_condition (storage_name, min_temp, max_temp, description)
VALUES
    ('Refrigerated_Storage', 0, 10, '신선식품 냉장 보관'),
    ('Frozen_Storage', -20, -1, '냉동식품 보관');

-- ✅ 구역 (냉장, 냉동)
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id)
VALUES
    (100, 'R01', 20000, 1, 1),
    (120, 'F01', 30000, 1, 2);

-- ✅ 입고
INSERT INTO inbound_table (
    inbound_date, inbound_request_date, inbound_status, admin_id,
    inbound_amount, product_id, reject_reason_code
) VALUES
      (NOW(), NOW(), '승인', 1, 80, 1, NULL), -- 상추
      (NOW(), NOW(), '승인', 1, 120, 4, NULL), -- 삼겹살
      (NOW(), NOW(), '승인', 1, 150, 6, NULL); -- 홍합

-- ✅ 출고
INSERT INTO outbound_table (
    outbound_date, outbound_request_date, outbound_status, admin_id,
    outbound_amount, product_id, reject_reason_code
) VALUES
      (NOW(), NOW(), '승인', 1, 30, 1, NULL),
      (NOW(), NOW(), '승인', 1, 50, 4, NULL),
      (NOW(), NOW(), '승인', 1, 60, 6, NULL);

-- ✅ 재고
INSERT INTO revenue_table (revenue_amount, product_id, area_id)
VALUES
    (50, 1, 1),  -- 상추 → 냉장
    (70, 4, 2),  -- 삼겹살 → 냉동
    (90, 6, 2);  -- 홍합 → 냉동

-- ✅ 재고 히스토리
-- 오류 방지: 시간 차이를 줘서 PRIMARY KEY 중복 방지
INSERT INTO revenue_history_table (
    revenue_id, change_date, revenue_quantity, change_type
) VALUES
      (1, NOW(), 80, '입고'),
      (1, DATE_ADD(NOW(), INTERVAL 1 SECOND), 30, '출고'),
      (2, DATE_ADD(NOW(), INTERVAL 2 SECOND), 120, '입고'),
      (2, DATE_ADD(NOW(), INTERVAL 3 SECOND), 50, '출고'),
      (3, DATE_ADD(NOW(), INTERVAL 4 SECOND), 150, '입고'),
      (3, DATE_ADD(NOW(), INTERVAL 5 SECOND), 60, '출고');
