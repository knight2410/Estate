set search_path to estate;

insert into role(code,name) values('MANAGER','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into district(code,name) values('Q1','Quận 1');
insert into district(code,name) values('Q2','Quận 2');
insert into district(code,name) values('Q3','Quận 3');
insert into district(code,name) values('Q4','Quận 4');
insert into district(code,name) values('Q5','Quận 5');
insert into district(code,name) values('Q6','Quận 6');
insert into district(code,name) values('Q7','Quận 7');
insert into district(code,name) values('Q8','Quận 8');
insert into district(code,name) values('Q9','Quận 9');
insert into district(code,name) values('Q10','Quận 10');
insert into district(code,name) values('Q11','Quận 11');
insert into district(code,name) values('Q12','Quận 12');
insert into district(code,name) values('QGV','Quận Gò Vấp ');
insert into district(code,name) values('QBT','Quận Bình Thạnh');
insert into district(code,name) values('QBT2','Quận Bình Tân');
insert into district(code,name) values('QTD','Quận Thủ Đức');
insert into district(code,name) values('QTP','Quận Tân Phú');
insert into district(code,name) values('QPN','Quận Phú Nhuận');
insert into district(code,name) values('QTB','Quận Tân Bình');
insert into district(code,name) values('HCC','Huyện Củ Chi');
insert into district(code,name) values('HBC','Huyện Bình Chánh');
insert into district(code,name) values('HHM','Huyện Hóc Môn');
insert into district(code,name) values('HCG','Huyện Cần Giờ');
insert into district(code,name) values('HNB','Huyện Nhà Bè');



insert into users(username,password,fullname,email,phone,status)
values('manager','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','manager','admin@gmail.com','0123456789',1);
insert into users(username,password,fullname,email,phone,status)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a','nguyenvana@gmail.com','0123456789',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);



