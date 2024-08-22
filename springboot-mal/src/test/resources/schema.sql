CREATE TABLE IF NOT EXISTS product
(
    product_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(128) NOT NULL,
    category VARCHAR(32) NOT NULL,
    image_url VARCHAR(256) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL,
    description VARCHAR(1024),
    created_date TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP NOT NULL
);

-- UNIQUE KEY 是My SQL 寫法 與H2 DB不同須注意轉換語法會出現Syntax error in SQL statement 錯誤導致所有測試不通過
-- 在Junit 5 test中因SQL 語句中 user是保留字所以須加上`` 連同測試的code內的SQL語句也是
CREATE TABLE IF NOT EXISTS `user`
(
    user_id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email              VARCHAR(256) NOT NULL UNIQUE,
    password           VARCHAR(256) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
);
