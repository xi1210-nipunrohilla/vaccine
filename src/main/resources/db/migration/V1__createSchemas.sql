CREATE TABLE IF NOT EXISTS branch_tb (
id INT AUTO_INCREMENT PRIMARY KEY,
branch_name VARCHAR(50),
location  VARCHAR(50),
start_time TIMESTAMP,
end_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS branch_schedule_tb(
branch_id INT,
time_date TIMESTAMP,
schedule_patient_id NUMERIC,
status VARCHAR(50),
created_on TIMESTAMP,
applied_on TIMESTAMP,
PRIMARY KEY (branch_id, time_date)
);

CREATE TABLE IF NOT EXISTS vaccine_tb(
vaccine_batch_id INT PRIMARY KEY,
vaccine_branch_id INT,
quantity NUMERIC
);

CREATE TABLE IF NOT EXISTS patient_tb(
patient_id NUMERIC PRIMARY KEY,
patient_name VARCHAR(50),
phone_number VARCHAR(50)
);