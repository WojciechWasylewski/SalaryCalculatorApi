-- DROP TABLE employee;
--
CREATE TABLE IF NOT EXISTS employee
(
    id BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255) NOT NULL,
    salaryBrutto NUMERIC
);