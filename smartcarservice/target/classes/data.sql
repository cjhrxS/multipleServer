INSERT INTO skills(name, required_time) VALUES('Service Technician', 2);
INSERT INTO skills(name, required_time) VALUES('Diagnostic Technician', 3);
INSERT INTO skills(name, required_time) VALUES('Transmission Technician', 4);
INSERT INTO skills(name, required_time) VALUES('Brake Technician', 2);
INSERT INTO skills(name, required_time) VALUES('Body Repair Technician', 5);
INSERT INTO skills(name, required_time) VALUES('Vehicle Refinisher', 3);
INSERT INTO skills(name, required_time) VALUES('Vehicle Inspector', 5);


INSERT INTO role(name) VALUES('ROLE_CAR_OWNER');
INSERT INTO role(name) VALUES('ROLE_SALES_MANAGER');
INSERT INTO role(name) VALUES('ROLE_TECHNICAL_MANAGER');
INSERT INTO role(name) VALUES('ROLE_DIELER');
INSERT INTO role(name) VALUES('ROLE_WORKER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

INSERT INTO fault_code(fault_code, description, type, skill_id)
VALUES ("B1315", "Problem in Front passenger child seat recognition","warning", 1),
("C1010","Battery Voltage Low","warning",2),
("C1012","Battery Voltage High","warning", 2),
("C1512", "Brakes Overheated","warning", 4),
("P1791", "Transmission Range Display Circ. Short to Ground","warning", 3),
("B1476", "Airbag malfunction indicator and warning lamp is defective","warning",5),
("P1296", "Engine/Transmission Control Modules Versions do not match","warning", 3),
("B1617", "Left/Right license plate lamp is defective","warning", 5),
("P1659", "Brake Switch Circ. Error Message from Engine Contr.","warning", 6);