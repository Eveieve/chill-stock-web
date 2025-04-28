
-- 새제품 입고 시 stock history에 신규 입고 이력 생성
DELIMITER $$

CREATE TRIGGER trg_stock_after_insert
    AFTER INSERT ON stock_table
    FOR EACH ROW
BEGIN
    INSERT INTO stock_history_table (
        stock_id,
        change_date,
        stock_quantity,
        change_type
    ) VALUES (
                 NEW.stock_id,
                 NOW(),
                 NEW.stock_amount,
                 '입고'
             );
END $$

DELIMITER ;

DROP TRIGGER IF EXISTS trg_stock_after_update;
DELIMITER $$

CREATE TRIGGER trg_stock_after_update
    AFTER UPDATE ON stock_table
    FOR EACH ROW
BEGIN
    INSERT INTO stock_history_table (
        stock_id,
        change_date,
        stock_quantity,
        change_type
    ) VALUES (
                 NEW.stock_id,
                 NOW(),
                 NEW.stock_amount - OLD.stock_amount, -- 변동된 수량만 기록
                 '입고'
             );
END $$

DELIMITER ;

