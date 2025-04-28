use chillstockDB;

show triggers ;

drop trigger if exists trg_outbound_status_approval;
DELIMITER $$
CREATE TRIGGER trg_outbound_status_approval
    AFTER UPDATE ON outbound_table
    FOR EACH ROW
BEGIN
    DECLARE stock_ida INT;
    DECLARE stock_amount1 INT;
    -- 출고 상태가 '승인'으로 변경되었는지 확인
    IF NEW.outbound_status = '승인' THEN

        -- stock_table에서 product_id에 해당하는 stock_id 가져오기
        SELECT stock_id
        INTO stock_ida
        FROM stock_table
        WHERE product_id = NEW.product_id
        LIMIT 1;

        SELECT stock_amount
        INTO stock_amount1
        FROM stock_table
        WHERE product_id = NEW.product_id
        LIMIT 1;

        IF stock_amount1 > NEW.outbound_amount THEN
            INSERT INTO stock_history_table (stock_id, stock_quantity, change_type)
            VALUES (stock_ida, stock_amount1 - NEW.outbound_amount, '출고');
        END IF;
    END IF;
END$$
DELIMITER ;
-- stock_table after update trigger 존재 -> stock_table after update trigger 생성불가
-- outbound_status 가 '승인' 으로 바뀌고 stock_amount 가 outbound_amount 보다 클때
-- (stock_amount - outbound_amount) 값을 stock_history_table 에 insert
