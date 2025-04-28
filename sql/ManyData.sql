use chillstockDb;

show tables;

select * from user_table;

INSERT INTO user_table
(user_login_id, user_email, user_name, user_password, user_phone, user_approved_at, user_type, user_status, user_requested_at)
VALUES
    ('admin1', 'admin1@example.com', '관리자1', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-9999-9999', NOW(), 'ROLE_admin', 'approve', NOW());

INSERT INTO user_table
(user_login_id, user_email, user_name, user_password, user_phone, user_approved_at, user_type, user_status, user_requested_at)
VALUES
    ('user1', 'user1@example.com', '사용자1', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0001-0001', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user2', 'user2@example.com', '사용자2', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0002-0002', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user3', 'user3@example.com', '사용자3', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0003-0003', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user4', 'user4@example.com', '사용자4', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0004-0004', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user5', 'user5@example.com', '사용자5', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0005-0005', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user6', 'user6@example.com', '사용자6', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0006-0006', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user7', 'user7@example.com', '사용자7', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0007-0007', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user8', 'user8@example.com', '사용자8', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0008-0008', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user9', 'user9@example.com', '사용자9', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0009-0009', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user10', 'user10@example.com', '사용자10', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0010-0010', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user11', 'user11@example.com', '사용자11', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0011-0011', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user12', 'user12@example.com', '사용자12', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0012-0012', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user13', 'user13@example.com', '사용자13', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0013-0013', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user14', 'user14@example.com', '사용자14', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0014-0014', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user15', 'user15@example.com', '사용자15', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0015-0015', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user16', 'user16@example.com', '사용자16', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0016-0016', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user17', 'user17@example.com', '사용자17', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0017-0017', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user18', 'user18@example.com', '사용자18', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0018-0018', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user19', 'user19@example.com', '사용자19', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0019-0019', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user20', 'user20@example.com', '사용자20', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0020-0020', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user21', 'user21@example.com', '사용자21', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0021-0021', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user22', 'user22@example.com', '사용자22', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0022-0022', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user23', 'user23@example.com', '사용자23', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0023-0023', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user24', 'user24@example.com', '사용자24', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0024-0024', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user25', 'user25@example.com', '사용자25', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0025-0025', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user26', 'user26@example.com', '사용자26', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0026-0026', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user27', 'user27@example.com', '사용자27', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0027-0027', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user28', 'user28@example.com', '사용자28', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0028-0028', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user29', 'user29@example.com', '사용자29', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0029-0029', NOW(), 'ROLE_member', 'approve', NOW()),
    ('user30', 'user30@example.com', '사용자30', '$2a$12$CDnSwcrbs6nj/bxPiZ4Wbexjy8GpLfJEd2TXyPYItViOznCqPuUsS', '010-0030-0030', NOW(), 'ROLE_member', 'approve', NOW());

INSERT INTO admin_table (admin_hire_date, admin_position, user_id)
VALUES
    (CURDATE(), '관리자', 1);

INSERT INTO business_table (business_regist_num, business_name, business_address, business_post, user_id) VALUES
                                                                                                              ('123-12-12345', 'SSG', '서울특별시 강남구 가로수길 5', '06035', 2),
                                                                                                              ('234-23-23456', '롯데', '부산광역시 해운대구 센텀남대로 35', '48058', 3),
                                                                                                              ('345-34-34567', '신세계', '대구광역시 동구 동대구로 455', '41101', 4),
                                                                                                              ('456-45-45678', '이마트', '광주광역시 서구 상무대로 884', '61949', 5),
                                                                                                              ('567-56-56789', '현대백화점', '인천광역시 연수구 송도국제대로 123', '21984', 6),
                                                                                                              ('678-67-67890', 'CJ', '대전광역시 유성구 대덕대로 512', '34126', 7),
                                                                                                              ('789-78-78901', 'GS리테일', '울산광역시 남구 삼산로 261', '44719', 8),
                                                                                                              ('890-89-89012', '코스트코', '경기도 수원시 영통구 덕영대로 1556', '16511', 9),
                                                                                                              ('901-90-90123', '홈플러스', '경기도 성남시 분당구 판교역로 146', '13524', 10),
                                                                                                              ('012-01-01234', '쿠팡', '제주특별자치도 제주시 첨단로 242', '63309', 11),
                                                                                                              ('123-02-12345', '배달의민족', '서울특별시 마포구 월드컵북로 400', '03925', 12),
                                                                                                              ('234-03-23456', '마켓컬리', '경기도 용인시 수지구 문인로 45', '16833', 13),
                                                                                                              ('345-04-34567', '네이버', '경기도 성남시 분당구 불정로 6', '13561', 14),
                                                                                                              ('456-05-45678', '카카오', '경기도 성남시 분당구 판교역로 235', '13494', 15),
                                                                                                              ('567-06-56789', '토스', '서울특별시 강남구 테헤란로 142', '06236', 16),
                                                                                                              ('678-07-67890', '무신사', '서울특별시 성동구 성수일로 77', '04782', 17),
                                                                                                              ('789-08-78901', '야놀자', '서울특별시 강남구 테헤란로 415', '06160', 18),
                                                                                                              ('890-09-89012', '쏘카', '서울특별시 마포구 월드컵북로 396', '03925', 19),
                                                                                                              ('901-10-90123', '11번가', '서울특별시 중구 한강대로 416', '04637', 20),
                                                                                                              ('012-11-01234', 'G마켓', '서울특별시 구로구 디지털로 300', '08379', 21),
                                                                                                              ('123-12-22345', '옥션', '서울특별시 구로구 디지털로26길 111', '08380', 22),
                                                                                                              ('234-13-33456', '티몬', '대전광역시 서구 대덕대로 325', '35229', 23),
                                                                                                              ('345-14-44567', '위메프', '광주광역시 북구 첨단연신로 160', '61088', 24),
                                                                                                              ('456-15-55678', 'SSG.COM', '부산광역시 사상구 사상로 310', '46972', 25),
                                                                                                              ('567-16-66789', '롯데ON', '인천광역시 부평구 경원대로 1367', '21417', 26),
                                                                                                              ('678-17-77890', '쿠팡이츠', '서울특별시 강서구 공항대로 227', '07803', 27),
                                                                                                              ('789-18-88901', '배민B마트', '경기도 고양시 일산동구 중앙로 1205', '10442', 28),
                                                                                                              ('890-19-99012', '배민상회', '경기도 부천시 부천로 3', '14623', 29),
                                                                                                              ('901-20-00123', '컬리넥스트마일', '경기도 하남시 미사강변서로 20', '12945', 30),
                                                                                                              ('012-21-11234', '오아시스마켓', '경기도 성남시 수정구 위례광장로 21', '13637', 31);

INSERT INTO category_main (category_name) VALUES
                                              ('채소'),
                                              ('과일'),
                                              ('곡물 및 견과'),
                                              ('육류'),
                                              ('수산물'),
                                              ('유제품'),
                                              ('냉동식품'),
                                              ('조미료 및 양념'),
                                              ('음료'),
                                              ('베이커리/디저트');

-- 채소 (1)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('잎채소', 1),
                                                              ('근채소', 1),
                                                              ('열매채소', 1),
                                                              ('향신채소', 1);

-- 과일 (2)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('열대과일', 2),
                                                              ('베리류', 2),
                                                              ('감귤류', 2),
                                                              ('사과배류', 2);

-- 곡물 및 견과 (3)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('쌀', 3),
                                                              ('보리', 3),
                                                              ('콩', 3),
                                                              ('견과류', 3);

-- 육류 (4)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('소고기', 4),
                                                              ('돼지고기', 4),
                                                              ('닭고기', 4),
                                                              ('양고기', 4);

-- 수산물 (5)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('생선', 5),
                                                              ('갑각류', 5),
                                                              ('조개류', 5),
                                                              ('해조류', 5);

-- 유제품 (6)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('우유', 6),
                                                              ('치즈', 6),
                                                              ('버터', 6),
                                                              ('요거트', 6);

-- 냉동식품 (7)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('냉동야채', 7),
                                                              ('냉동해산물', 7),
                                                              ('냉동과일', 7),
                                                              ('냉동완제품', 7);

-- 조미료 및 양념 (8)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('소금', 8),
                                                              ('설탕', 8),
                                                              ('간장', 8),
                                                              ('된장/고추장', 8);

-- 음료 (9)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('주스', 9),
                                                              ('탄산음료', 9),
                                                              ('생수', 9),
                                                              ('커피음료', 9);

-- 베이커리/디저트 (10)
INSERT INTO c_mid_level (category_name, category_main_id) VALUES
                                                              ('빵류', 10),
                                                              ('케이크류', 10),
                                                              ('과자류', 10),
                                                              ('떡류', 10);


INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (24, '오이', 8, -18, '2026-04-18', 20);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (17, '랍스터', 11, 15, '2026-01-19', 11);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (15, '돼지고기 삼겹살', 11, -65, '2025-08-12', 18);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (18, '토마토', 5, 43, '2025-12-23', 9);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (14, '망고', 14, 11, '2025-07-30', 15);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (24, '바나나', 1, -17, '2025-08-24', 26);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (28, '망고', 3, 3, '2026-03-21', 6);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (16, '오이', 13, 5, '2025-08-24', 15);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (4, '소고기 안심', 8, -80, '2025-10-23', 21);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (5, '냉동만두', 15, 31, '2026-04-22', 15);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (7, '소고기 등심', 8, 2, '2025-11-29', 10);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (23, '소고기 안심', 7, 49, '2026-01-18', 29);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (16, '배', 16, -14, '2025-12-15', 24);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (2, '치즈', 6, -16, '2026-02-09', 24);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (20, '냉동만두', 13, -74, '2025-09-23', 15);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (8, '냉동만두', 6, -17, '2025-12-13', 18);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (13, '보리', 2, 42, '2025-07-22', 6);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (23, '냉동만두', 9, 10, '2025-10-13', 10);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (3, '사과', 6, -10, '2025-10-29', 24);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (8, '돼지고기 목살', 6, -14, '2025-06-05', 16);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (29, '우유', 14, -66, '2025-06-11', 26);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (15, '상추', 2, 14, '2025-08-04', 3);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (23, '간장', 3, 6, '2025-12-28', 30);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (14, '바나나', 14, -18, '2026-02-10', 21);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (14, '배추', 10, 24, '2025-10-08', 3);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (20, '보리', 14, 7, '2025-10-31', 13);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (19, '토마토', 11, 12, '2025-07-30', 4);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (11, '고추장', 6, 17, '2025-08-31', 23);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (19, '돼지고기 삼겹살', 2, 44, '2025-07-07', 6);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (20, '우유', 2, -14, '2025-08-03', 17);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (20, '광어', 4, 7, '2025-09-03', 17);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (2, '망고', 8, 5, '2025-07-27', 26);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (18, '보리', 2, -66, '2026-01-31', 5);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (14, '오이', 7, 15, '2025-06-05', 7);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (30, '고추장', 6, 11, '2025-09-29', 15);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (12, '바나나', 14, -55, '2025-12-07', 16);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (20, '보리', 4, 0, '2025-07-18', 16);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (26, '배추', 12, -54, '2025-11-09', 30);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (8, '간장', 3, 45, '2025-10-26', 8);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (22, '간장', 2, -54, '2025-08-28', 4);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (23, '연어', 13, 0, '2025-06-05', 11);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (22, '배', 4, 15, '2025-10-01', 5);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (25, '오이', 3, 6, '2026-02-05', 29);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (16, '소고기 안심', 4, 5, '2025-06-22', 27);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (27, '배', 8, 14, '2026-04-18', 19);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (27, '간장', 11, -77, '2025-12-16', 25);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (27, '냉동만두', 15, 42, '2025-07-12', 16);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (7, '소고기 안심', 8, 20, '2026-01-11', 11);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (13, '사과', 7, -11, '2026-04-01', 26);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (19, '상추', 16, -19, '2025-10-29', 14);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (25, '소고기 등심', 2, 32, '2025-09-29', 13);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (18, '배추', 4, 12, '2025-12-25', 29);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (4, '바나나', 10, -12, '2025-08-17', 10);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (18, '우유', 14, -73, '2025-06-19', 8);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (14, '치즈', 13, 1, '2025-11-21', 8);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (8, '배', 4, 38, '2025-06-14', 18);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (4, '치즈', 11, 6, '2025-10-31', 6);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (13, '쌀', 12, 5, '2026-04-14', 21);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (8, '오이', 3, 10, '2025-10-08', 30);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (5, '배', 2, -59, '2025-06-08', 27);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (28, '바나나', 9, -17, '2026-04-22', 8);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (12, '망고', 13, -12, '2025-11-10', 21);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (2, '냉동만두', 8, -55, '2025-11-19', 4);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (6, '소고기 등심', 6, 15, '2026-02-11', 8);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (15, '상추', 2, 23, '2025-06-30', 14);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (10, '소고기 등심', 13, 38, '2025-09-17', 20);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (22, '소고기 등심', 14, -17, '2026-03-20', 25);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (14, '치즈', 11, 0, '2025-05-28', 13);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (22, '사과', 1, -11, '2025-12-23', 1);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (8, '보리', 15, 10, '2026-01-23', 3);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (5, '망고', 14, -20, '2025-07-03', 18);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (8, '우유', 2, 22, '2026-03-03', 12);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (18, '랍스터', 14, 37, '2026-03-23', 26);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (18, '대하', 3, -16, '2025-09-12', 24);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (2, '돼지고기 삼겹살', 2, 3, '2025-11-06', 20);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (15, '쌀', 9, -20, '2025-08-22', 23);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (6, '간장', 14, 16, '2026-04-07', 29);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (24, '오이', 3, -14, '2025-07-21', 1);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (21, '연어', 5, 21, '2026-01-17', 5);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (10, '돼지고기 목살', 5, -17, '2025-08-05', 30);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (25, '토마토', 12, -59, '2025-12-24', 3);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (19, '사과', 13, 21, '2025-08-05', 12);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (30, '소고기 등심', 15, -16, '2025-12-20', 5);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (9, '오이', 14, -17, '2025-09-21', 15);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (12, '보리', 2, -64, '2025-09-09', 11);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (23, '치즈', 10, 48, '2025-12-27', 7);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (14, '랍스터', 11, -57, '2025-11-13', 27);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (17, '소고기 안심', 7, 3, '2025-10-13', 17);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (3, '상추', 2, 5, '2026-03-23', 16);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (2, '쌀', 2, 0, '2026-03-23', 22);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (1, '우유', 14, 20, '2026-01-01', 26);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (22, '상추', 9, 3, '2025-06-22', 1);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (22, '소고기 등심', 10, -60, '2025-07-04', 25);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (6, '배', 7, 10, '2025-06-22', 15);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (5, '배', 11, 42, '2025-09-16', 5);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (27, '망고', 12, -60, '2026-03-02', 13);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (18, '냉동만두', 11, -71, '2025-10-08', 6);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (4, '간장', 4, 50, '2025-11-30', 4);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (11, '바나나', 3, 5, '2025-11-07', 21);
INSERT INTO product (product_size, product_name, category_mid_id, storage_temperature, expiration_date, business_id)
VALUES (1, '사과', 15, 15, '2025-07-26', 29);

INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('서울창고', 11642, '서울특별시 중구 세종대로 110', 2250);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('경기창고', 19215, '경기도 수원시 팔달구 효원로 241', 1306);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('강원창고', 16786, '강원도 춘천시 금강로 45', 2503);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('충남창고', 15807, '충청남도 홍성군 홍북읍 충남대로 21', 2212);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('충북창고', 11504, '충청북도 청주시 상당구 상당로 82', 2525);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('경남창고', 11204, '경상남도 창원시 의창구 중앙대로 151', 1562);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('경북창고', 17570, '경상북도 안동시 퇴계로 120', 1861);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('전남창고', 17979, '전라남도 무안군 삼향읍 오룡길 1', 763);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('전북창고', 12247, '전라북도 전주시 완산구 기린대로 227', 2621);
INSERT INTO warehouse_table (warehouse_name, warehouse_space, warehouse_address, warehouse_amount) VALUES ('제주창고', 19629, '제주특별자치도 제주시 중앙로 14', 1960);



INSERT INTO storage_condition (storage_name, min_temp, max_temp, description)
VALUES
    ('Ultra-Low_Temp', -80, -50, '극저온'),
    ('Frozen_Storage', -20, -10, '육류 보관'),
    ('Refrigerated_Storage', 0, 5, '채소/과일 보관'),
    ('Cool_Storage', 5, 15, '과일/음료'),
    ('Room_Temperature', 15, 25, '상온 보관'),
    ('Heated_Storage', 30, 50, '발효/보온');


INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (814, 'A-1-543039', 1145, 1, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (821, 'B-1-718227', 1852, 1, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (935, 'C-1-896383', 3053, 1, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1208, 'D-1-578713', 2340, 1, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (865, 'E-1-509839', 2304, 1, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (829, 'F-1-103105', 1080, 1, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1326, 'A-2-347382', 4372, 2, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1048, 'B-2-763116', 3402, 2, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1667, 'C-2-670106', 3279, 2, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1023, 'D-2-333872', 3956, 2, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1551, 'E-2-473178', 1302, 2, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1214, 'F-2-801326', 4478, 2, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1292, 'A-3-360260', 3698, 3, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1071, 'B-3-746872', 2055, 3, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1103, 'C-3-309805', 968, 3, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (902, 'D-3-978820', 4660, 3, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1128, 'E-3-219136', 1482, 3, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (2816, 'F-3-990916', 4782, 3, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (961, 'A-4-435346', 1572, 4, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1107, 'B-4-751079', 1319, 4, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (875, 'C-4-838425', 1063, 4, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1300, 'D-4-542784', 4833, 4, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (816, 'E-4-841241', 1376, 4, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (3840, 'F-4-824493', 3308, 4, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (826, 'A-5-487401', 3969, 5, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (870, 'B-5-005242', 4119, 5, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1082, 'C-5-680112', 4969, 5, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (818, 'D-5-598262', 842, 5, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1115, 'E-5-505331', 3397, 5, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (2464, 'F-5-923226', 703, 5, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (811, 'A-6-563421', 3633, 6, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (809, 'B-6-733754', 2364, 6, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (914, 'C-6-036541', 2786, 6, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (979, 'D-6-868501', 2639, 6, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (982, 'E-6-940196', 3331, 6, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (2084, 'F-6-698169', 2057, 6, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1060, 'A-7-060883', 3483, 7, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1241, 'B-7-159514', 4654, 7, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1116, 'C-7-656482', 2071, 7, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1661, 'D-7-629946', 4988, 7, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (801, 'E-7-443699', 3139, 7, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (4609, 'F-7-773872', 1194, 7, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1090, 'A-8-895134', 2340, 8, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1003, 'B-8-200379', 1096, 8, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1732, 'C-8-693676', 2498, 8, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1102, 'D-8-016328', 4305, 8, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1005, 'E-8-831727', 4851, 8, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (5378, 'F-8-957986', 4988, 8, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (914, 'A-9-277434', 4770, 9, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (924, 'B-9-347143', 2725, 9, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (971, 'C-9-581223', 3637, 9, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (956, 'D-9-316658', 4316, 9, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1651, 'E-9-036690', 3616, 9, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (2753, 'F-9-054668', 4973, 9, 6);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1417, 'A-10-373467', 737, 10, 1);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1198, 'B-10-562729', 4875, 10, 2);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (855, 'C-10-699016', 1611, 10, 3);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (1745, 'D-10-204653', 4224, 10, 4);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (2138, 'E-10-564641', 4352, 10, 5);
INSERT INTO area_table (area_space, area_code, area_price, warehouse_id, storage_id) VALUES (958, 'F-10-805310', 754, 10, 6);



select * from inbound_table;



