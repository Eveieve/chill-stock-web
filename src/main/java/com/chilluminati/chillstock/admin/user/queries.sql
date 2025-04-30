use chillstockDB;
select * from user_backup_table;
drop table if exists user_table;
drop table if exists business_table;

CREATE TABLE user_table (
                            user_id INT AUTO_INCREMENT PRIMARY KEY,
                            user_login_id VARCHAR(50) NOT NULL UNIQUE,
                            user_email VARCHAR(100),
                            user_name VARCHAR(30) NOT NULL,
                            user_password VARCHAR(255) NOT NULL,
                            user_phone VARCHAR(20),
                            user_approved_at DATETIME,
                            user_type ENUM('ADMIN', 'MEMBER') DEFAULT 'MEMBER',
                            user_status ENUM('APPROVED', 'PENDING') DEFAULT 'PENDING',
                            user_requested_at DATETIME

);


CREATE TABLE business_table (
                                business_id INT AUTO_INCREMENT PRIMARY KEY,
                                business_regist_num VARCHAR(20) NOT NULL UNIQUE,
                                business_name VARCHAR(255),
                                business_address VARCHAR(255) NOT NULL,
                                business_post VARCHAR(10) NOT NULL,
                                user_id INT NOT NULL
);

CREATE TABLE user_backup_table (
                                   backup_id INT AUTO_INCREMENT PRIMARY KEY,
                                   user_login_id VARCHAR(50) NOT NULL,
                                   user_email VARCHAR(100),
                                   user_name VARCHAR(30) NOT NULL,
                                   user_phone VARCHAR(20),
                                   approved_at DATETIME,
                                   requested_at DATETIME,
                                   business_regist_num VARCHAR(20),
                                   business_name VARCHAR(255),
                                   business_address VARCHAR(255),
                                   business_post VARCHAR(10),
                                   deleted_at DATETIME DEFAULT CURRENT_TIMESTAMP
);


DELIMITER $$

CREATE TRIGGER trg_before_user_delete_with_biz
    BEFORE DELETE ON user_table
    FOR EACH ROW
BEGIN
    DECLARE v_business_regist_num VARCHAR(20);
    DECLARE v_business_name VARCHAR(255);
    DECLARE v_business_address VARCHAR(255);
    DECLARE v_business_post VARCHAR(10);

    -- business_table에서 user_id 기준으로 사업체 정보 조회
    SELECT
        b.business_regist_num,
        b.business_name,
        b.business_address,
        b.business_post
    INTO
        v_business_regist_num,
        v_business_name,
        v_business_address,
        v_business_post
    FROM business_table b
    WHERE b.user_id = OLD.user_id
        LIMIT 1;

    -- user_backup_table에 회원 정보 + 사업체 정보 함께 저장
    INSERT INTO user_backup_table (
        user_login_id,
        user_email,
        user_name,
        user_phone,
        deleted_at,
        approved_at,
        requested_at,
        business_regist_num,
        business_name,
        business_address,
        business_post
    )
    VALUES (
               OLD.user_login_id,
               OLD.user_email,
               OLD.user_name,
               OLD.user_phone,
               NOW(),
               OLD.user_approved_at,
               OLD.user_requested_at,
               v_business_regist_num,
               v_business_name,
               v_business_address,
               v_business_post
           );
END $$

DELIMITER ;