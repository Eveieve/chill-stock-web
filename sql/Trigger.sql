-- 회원 삭제될 시 이용되는 트리터
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