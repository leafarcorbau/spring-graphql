DROP TABLE IF EXISTS address;

CREATE TABLE address (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  street VARCHAR(250) NOT NULL,
  house_number INT NOT NULL,
  employee_id INT
);