drop table if exists odetails;
drop table if exists orders;
drop table if exists employees;
drop table if exists customers;
drop table if exists zipcodes;
drop table if exists parts;






CREATE TABLE zipcodes(zip INT NOT NULL, city CHAR(15),
						PRIMARY KEY(zip)) ENGINE=INNODB;

CREATE TABLE customers(cno INT NOT NULL, cname CHAR(10), street CHAR(20),
						zip INT(5), phone CHAR(12),
						PRIMARY KEY(cno),
						FOREIGN KEY(zip)
						REFERENCES zipcodes(zip)
						ON UPDATE CASCADE ON DELETE RESTRICT) ENGINE=INNODB;
 

CREATE TABLE employees(eno INT NOT NULL, ename CHAR(10), zip INT(5), hdate DATE, 
						PRIMARY KEY(eno),
						FOREIGN KEY(zip)
						REFERENCES zipcodes(zip)
						ON UPDATE CASCADE ON DELETE RESTRICT) ENGINE=INNODB;
						
CREATE TABLE parts(pno INT NOT NULL, pname CHAR(20), qoh INT NOT NULL, price DOUBLE NOT NULL, 
						level INT NOT NULL,
						PRIMARY KEY(pno)) ENGINE=INNODB;
						
CREATE TABLE orders(ono INT NOT NULL, cno INT NOT NULL, eno INT NOT NULL, received DATE, shipped DATE,
						PRIMARY KEY(ono),
						FOREIGN KEY(cno)
						REFERENCES customers(cno)
						ON UPDATE CASCADE ON DELETE RESTRICT,
						FOREIGN KEY(eno)
						REFERENCES employees(eno)
						ON UPDATE CASCADE ON DELETE RESTRICT) ENGINE=INNODB;
			
CREATE TABLE odetails(ono INT NOT NULL, pno INT NOT NULL, qty INT(4),
						PRIMARY KEY(ono, pno),
						FOREIGN KEY(ono)
						REFERENCES orders(ono)
						ON UPDATE CASCADE ON DELETE RESTRICT,
						FOREIGN KEY(pno)
						REFERENCES parts(pno)
						ON UPDATE CASCADE ON DELETE RESTRICT) ENGINE=INNODB;
						
						
insert into zipcodes values(67226, 'Wichita');
insert into zipcodes values(60606, 'Fort Dodge');
insert into zipcodes values(50302, 'Kansas City');
insert into zipcodes values(54444, 'Columbia');
insert into zipcodes values(66002, 'Liberal');
insert into zipcodes values(61111, 'Fort Hays');
insert into zipcodes values(28411, 'Wilmington');
insert into zipcodes values(28408, 'Wilmington');
						
insert into parts values(10506, 'Land Before Time I', 200, 19.99, 20);
insert into parts values(10507, 'Land Before Time II', 156, 19.99, 20);
insert into parts values(10508, 'Land Before Time III', 190, 19.99, 20);
insert into parts values(10509, 'Land Before Time IV', 60, 19.99, 20);
insert into parts values(10601, 'Sleeping Beauty', 300, 24.99, 20);
insert into parts values(10701, 'When Harry Met Sally', 120, 19.99, 30);
insert into parts values(10800, 'Dirty Harry', 140, 14.99, 30);
insert into parts values(10900, 'Dr. Zhivago', 100, 24.99, 30);
insert into parts values(10901, 'A Star is Born', 100, 4.99, 30);
insert into parts values(10902, 'Star Wars', 500, 24.99, 30);
insert into parts values(10903, 'Lord of the Rings', 100, 34.99, 30);
						
insert into customers values(1111, 'Charles', '123 Main St.', 67226, '316-636-555');
insert into customers values(2222, 'Bertram', '237 Ash Avenue', 67226, '316-636-555');
insert into customers values(3333, 'Barbara', '111 Inwood St.', 60606, '316-111-1234');
insert into customers values(4444, 'Will', '111 Kenwood St.', 54444, '416-111-1234');
insert into customers values(5555, 'Bill', '211 Marlwood St.', 28408, '416-111-1235');
insert into customers values(6666, 'Keely', '211 Pinewood St.', 28411, '416-111-1235');
insert into customers values(7777, 'Maera', '211 Marlwood St.', 28408, '416-111-1235');

insert into employees values(1000, 'Jones', 67226, '1995-12-12');
insert into employees values(1001, 'Smith', 67226, '1992-01-01');
insert into employees values(1002, 'Brown', 67226, '1994-09-01');
insert into employees values(1003, 'Green', 67226, '2002-09-01');
insert into employees values(1004, 'Purple', 67226, '2003-01-01');



insert into orders values(1020, 1111, 1000, '2017-12-10', '2017-12-12');
insert into orders values(1021, 1111, 1000, '2016-01-12', '2016-01-15');
insert into orders values(1022, 2222, 1001, '1995-02-13', '1995-02-20');
insert into orders values(1023, 3333, 1000, '2016-02-15', NULL);
insert into orders values(1024, 4444, 1000, '2017-02-15', '2017-02-16');
insert into orders values(1025, 5555, 1000, '2015-02-15', '2015-02-16');

insert into odetails values(1020, 10506, 1);
insert into odetails values(1020, 10800, 1);
insert into odetails values(1020, 10508, 2);
insert into odetails values(1020, 10509, 3);
insert into odetails values(1021, 10601, 4);
insert into odetails values(1021, 10506, 7);
insert into odetails values(1022, 10601, 1);
insert into odetails values(1022, 10701, 1);
insert into odetails values(1023, 10800, 1);
insert into odetails values(1023, 10900, 1);
insert into odetails values(1023, 10506, 2);
insert into odetails values(1024, 10506, 12);
insert into odetails values(1025, 10601, 2);



						
						
