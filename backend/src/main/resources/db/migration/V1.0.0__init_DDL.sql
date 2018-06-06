CREATE TABLE users(
    id BIGINT NOT NULL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE categories(
    id BIGINT NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NULL
);

ALTER TABLE categories
    ADD CONSTRAINT FK_CategoriesUsers
    FOREIGN KEY (user_id) REFERENCES users(id);

CREATE TABLE sub_categories(
    id BIGINT NOT NULL PRIMARY KEY,
    category_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE sub_categories
    ADD CONSTRAINT FK_SubCategoriesCategories
    FOREIGN KEY (category_id) REFERENCES categories(id);


CREATE TABLE transactions(
    id BIGINT NOT NULL PRIMARY KEY,
    sub_category_id BIGINT NOT NULL,
    date TIMESTAMP NOT NULL,
    amount DECIMAL(22, 5) NOT NULL,
    description VARCHAR(255) NULL
);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TransactionsSubCategories
    FOREIGN KEY (sub_category_id) REFERENCES sub_categories(id);