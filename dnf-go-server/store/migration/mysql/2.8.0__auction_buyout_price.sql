-- 2026-09-07 第五十八轮: 拍卖一口价(buyout_price)落库, 独立于起拍价 price
ALTER TABLE auction_item ADD COLUMN buyout_price BIGINT NOT NULL DEFAULT 0 COMMENT '一口价, 0=未设置(取起拍价)' AFTER price;
