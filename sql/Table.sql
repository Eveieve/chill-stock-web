-- 외래키 무시하고 삭제 (초기화용)
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS stock_history_table;
DROP TABLE IF EXISTS stock_table;
DROP TABLE IF EXISTS outbound_table;
DROP TABLE IF EXISTS inbound_table;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS c_mid_level;
DROP TABLE IF EXISTS category_main;
DROP TABLE IF EXISTS business_table;
DROP TABLE IF EXISTS admin_table;
DROP TABLE IF EXISTS login_h_table;
DROP TABLE IF EXISTS user_table;
DROP TABLE IF EXISTS area_table;
DROP TABLE IF EXISTS warehouse_table;
DROP TABLE IF EXISTS storage_condition;
DROP TABLE IF EXISTS user_backup_table;

SET FOREIGN_KEY_CHECKS = 1;

-- ✅ 회원 테이블
CREATE TABLE user_table (
                            user_id INT AUTO_INCREMENT PRIMARY KEY,
                            user_login_id VARCHAR(50) NOT NULL UNIQUE,
                            user_email VARCHAR(100),
                            user_name VARCHAR(30) NOT NULL,
                            user_password VARCHAR(255) NOT NULL,
                            user_phone VARCHAR(20),
                            user_approved_at DATETIME,
                            user_type ENUM('ROLE_admin', 'ROLE_member') NOT NULL,
                            user_status ENUM('approve', 'pending') DEFAULT 'pending',
                            user_requested_at DATETIME
);

-- ✅ 관리자 테이블
CREATE TABLE admin_table (
                             admin_id INT AUTO_INCREMENT PRIMARY KEY,
                             admin_hire_date DATE NOT NULL,
                             admin_position VARCHAR(20),
                             user_id INT NOT NULL UNIQUE
);

-- ✅ 사업체 테이블
CREATE TABLE business_table (
                                business_id INT AUTO_INCREMENT PRIMARY KEY,
                                business_regist_num VARCHAR(20) NOT NULL UNIQUE,
                                business_name VARCHAR(255) NOT NULL,
                                business_address VARCHAR(255) NOT NULL,
                                business_post VARCHAR(10) NOT NULL,
                                user_id INT NOT NULL
);

-- ✅ 카테고리 대분류
CREATE TABLE category_main (
                               category_id INT AUTO_INCREMENT PRIMARY KEY,
                               category_name VARCHAR(50) NOT NULL UNIQUE
);

-- ✅ 카테고리 중분류
CREATE TABLE c_mid_level (
                             category_mid_id INT AUTO_INCREMENT PRIMARY KEY,
                             category_name VARCHAR(50) NOT NULL,
                             category_main_id INT NOT NULL
);

-- ✅ 제품 테이블
CREATE TABLE product (
                         product_id INT AUTO_INCREMENT PRIMARY KEY,
                         product_size INT NOT NULL,
                         product_name VARCHAR(50) NOT NULL,
                         category_mid_id INT NOT NULL,
                         storage_temperature INT,
                         expiration_date DATE,
                         business_id INT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ✅ 입고 테이블
CREATE TABLE inbound_table (
                               inbound_id INT AUTO_INCREMENT PRIMARY KEY,
                               inbound_date TIMESTAMP,
                               inbound_request_date TIMESTAMP NOT NULL,
                               inbound_status ENUM('승인', '반려', '대기') DEFAULT '대기',
                               admin_id INT,
                               inbound_amount INT NOT NULL,
                               product_id INT NOT NULL,
                               reject_reason_code CHAR(10)
);

-- ✅ 출고 테이블
CREATE TABLE outbound_table (
                                outbound_id INT AUTO_INCREMENT PRIMARY KEY,
                                outbound_date TIMESTAMP,
                                outbound_request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                outbound_status ENUM('승인', '대기', '취소') DEFAULT '대기',
                                admin_id INT,
                                outbound_amount INT NOT NULL,
                                product_id INT NOT NULL,
                                reject_reason_code CHAR(10)
);

-- ✅ 창고 테이블
CREATE TABLE warehouse_table (
                                 warehouse_id INT AUTO_INCREMENT PRIMARY KEY,
                                 warehouse_name VARCHAR(20) NOT NULL,
                                 warehouse_space INT NOT NULL,
                                 warehouse_address VARCHAR(200) NOT NULL,
                                 warehouse_amount INT NOT NULL DEFAULT 0
);

-- ✅ 구역 테이블
CREATE TABLE area_table (
                            area_id INT AUTO_INCREMENT PRIMARY KEY,
                            area_space INT NOT NULL,
                            area_code VARCHAR(50) NOT NULL,
                            area_price INT NOT NULL,
                            warehouse_id INT NOT NULL,
                            storage_id INT
);

-- ✅ 보관 상태 테이블
CREATE TABLE storage_condition (
                                   storage_id INT AUTO_INCREMENT PRIMARY KEY,
                                   storage_name ENUM(
                                       'Ultra-Low_Temp', 'Frozen_Storage', 'Refrigerated_Storage',
                                       'Cool_Storage', 'Room_Temperature', 'Heated_Storage'
                                       ) NOT NULL,
                                   min_temp INT NOT NULL,
                                   max_temp INT NOT NULL,
                                   description TEXT
);

-- ✅ 재고 테이블 (stock)
CREATE TABLE stock_table (
                             stock_id INT AUTO_INCREMENT PRIMARY KEY,
                             stock_amount INT NOT NULL,
                             product_id INT NOT NULL,
                             area_id INT NOT NULL
);

-- ✅ 재고 히스토리 테이블 (stock_history)
CREATE TABLE stock_history_table (
                                     stock_id INT NOT NULL,
                                     change_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                     stock_quantity INT NOT NULL,
                                     change_type ENUM('입고', '출고') NOT NULL,
                                     PRIMARY KEY (stock_id, change_date)
);

-- ✅ 회원 백업 테이블
CREATE TABLE user_backup_table (
                                   backup_id INT AUTO_INCREMENT PRIMARY KEY,
                                   user_login_id VARCHAR(50) NOT NULL,
                                   user_email VARCHAR(100),
                                   user_name VARCHAR(30) NOT NULL,
                                   user_phone VARCHAR(20),
                                   deleted_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   approved_at DATETIME,
                                   requested_at DATETIME
);

-- ✅ 제약 조건 (FK 설정)
-- 관리자 FK
ALTER TABLE admin_table
    ADD CONSTRAINT fk_admin_user FOREIGN KEY (user_id) REFERENCES user_table(user_id) ON DELETE CASCADE;

-- 사업체 FK
ALTER TABLE business_table
    ADD CONSTRAINT fk_business_user FOREIGN KEY (user_id) REFERENCES user_table(user_id) ON DELETE CASCADE;

-- 카테고리 FK
ALTER TABLE c_mid_level
    ADD CONSTRAINT fk_category_mid_main FOREIGN KEY (category_main_id) REFERENCES category_main(category_id) ON DELETE CASCADE;

-- 제품 FK
ALTER TABLE product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (category_mid_id) REFERENCES c_mid_level(category_mid_id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_product_business FOREIGN KEY (business_id) REFERENCES business_table(business_id) ON DELETE CASCADE;

-- 입고 FK
ALTER TABLE inbound_table
    ADD CONSTRAINT fk_inbound_admin FOREIGN KEY (admin_id) REFERENCES admin_table(admin_id),
    ADD CONSTRAINT fk_inbound_product FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE;

-- 출고 FK
ALTER TABLE outbound_table
    ADD CONSTRAINT fk_outbound_admin FOREIGN KEY (admin_id) REFERENCES admin_table(admin_id),
    ADD CONSTRAINT fk_outbound_product FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE;

-- 창고 FK
ALTER TABLE area_table
    ADD CONSTRAINT fk_area_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse_table(warehouse_id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_area_storage FOREIGN KEY (storage_id) REFERENCES storage_condition(storage_id) ON DELETE SET NULL;

-- 재고 FK
ALTER TABLE stock_table
    ADD CONSTRAINT fk_stock_product FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_stock_area FOREIGN KEY (area_id) REFERENCES area_table(area_id) ON DELETE CASCADE;

-- 재고 히스토리 FK
ALTER TABLE stock_history_table
    ADD CONSTRAINT fk_stock_history_inventory FOREIGN KEY (stock_id) REFERENCES stock_table(stock_id) ON DELETE CASCADE;


ALTER TABLE inbound_table MODIFY admin_id INT NULL;
ALTER TABLE outbound_table MODIFY COLUMN admin_id INT NULL;
-- null 허용 가능