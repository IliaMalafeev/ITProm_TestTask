-- Inserting 3 default professions --
insert into profession (id, title, description) values (10000001, 'Profession 1', 'Description 1');
insert into profession (id, title, description) values (10000002, 'Profession 2', 'Description 2');
insert into profession (id, title, description) values (10000003, 'Profession 3', 'Description 3');

-- Inserting 3 default departments --
insert into department (id, title, additional_info, parent_department_id) values (10000001, 'Department 1', 'Additional Info 1', null);
insert into department (id, title, additional_info, parent_department_id) values (10000002, 'Department 2', 'Additional Info 2', 10000001);
insert into department (id, title, additional_info, parent_department_id) values (10000003, 'Department 3', 'Additional Info 3', 10000001);

-- Inserting 3 default employees --
insert into employee (id, full_name, additional_info, profession_id, department_id) values (10000001, 'Full Name 1', 'Additional Info 1', 10000001, 10000001);
insert into employee (id, full_name, additional_info, profession_id, department_id) values (10000002, 'Full Name 2', 'Additional Info 2', 10000002, 10000002);
insert into employee (id, full_name, additional_info, profession_id, department_id) values (10000003, 'Full Name 3', 'Additional Info 3', 10000003, 10000003);
