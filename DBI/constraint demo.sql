use master
go
if exists(select name from sysdatabases where name='demo')
drop Database demo
go
Create Database demo
go
use demo
go
create table Class(classID varchar(30) primary key,className nvarchar(60) not null,
maxAmount int check(maxamount>=0 and maxAmount<=30), status bit default 1)
go
/*
insert into tablename values(paralist as fieldindesign)
*/

insert into Class values('class01', 'DBI', 29,1)
go 
select * from Class
--problem
insert into Class values('java', 'class02', 30,1)
insert into Class values('class03', 'C', 29)
go
/*
insert into tablename([list of fields]) values (paralist of [listof field])
*/
insert into Class(classID, className, maxAmount,status)
values('Class03', 'C', 28, 1)
insert into Class(className, classID, maxAmount, status)
values('java', 'class02', 30, 1)
insert into Class(classID, className, maxAmount)
values('class04', 'HTML', 26)
--error
insert into Class(classID, className, maxAmount)
values('class04', 'SQL', 26)
insert into Class(className, maxAmount) values ('SQL', 26)

insert into Class(classID, maxAmount)
values('class04', 26)
insert into Class(classID, className, maxAmount, status)
values('class07', 'web', 30, 0)
insert into Class(classID, className, maxAmount) 
values('class08', 'webdesign', 30)
--identity ~ autonumber
--identity(start, increment)
--unique: not duplicate
create table Student(stuId int identity(1,1) primary key, 
stuName nvarchar(50) not null, email varchar(30) unique,
birthday datetime default getdate(), description nvarchar(max),
classID varchar(30) foreign key references Class(classID))
/*
create table demo(key1 varchar(30), key2 varchar(40), 
unique(key1, key2), id int identity(1,1) primary key)
*/
insert into Student(stuId, stuName, email, description, classID)
values(1, 'A Hoang', 'HoangA@fpt.edu.vn', '2/17/2000', 'new', 'class01')
insert into Student(stuName, email, birthday, description, classID)
values('A Hoang', 'HoangA@fpt.edu.vn', '2/17/2000', 'new', 'class01')
select * from Student
select * from Class
--chi nhap khoa ngoai da ton tai class01
insert into Student(stuName, email, birthday, description, classID)
values('lugowo', 'lugowo@fpt.edu.vn', '2/17/2000', 'new', 'class10')
insert into Student(stuName, email, birthday, description, classID)
values('lugowo', 'lugowo@fpt.edu.vn', '2/17/2000', 'new', 'class02')
insert into Student(stuName, birthday, description, classID)
values('lugow222', '1/17/2000', 'new', 'class02')
/*
 - insert ben 1 truoc ben nhieu sau
 - khoa chinh: no duplicate,no empty (not null), không insert identity
 - foreign key: chỉ nhập giá trị đã tồn tại trên khóa chính
 - not null: bắt buộc phải nhập
 - check: chỉ nhập giá trị đúng điều kiện
 - unique: không nhập trùng nhau
 - default: có thể bỏ qua không nhập
 - dateTime: nhập kiểu string theo config của máy
 - string trong '', số thì viết bình thường
 */

 select * from Class
 select * from Student
 go
 insert into Student values('asdf','asdf@asdf.com','12/2/2000','asdf','class01')
 update Class set maxAmount = 30
 select * from Class
 update class set maxAmount=0 where status = 0
 update class set className='PRO192' where classID = 'class02'
 update class set className='web201c', maxAmount *= 0.9 where className = 'HTML'
 update class set status = 1 where className='web' and status = 0
 update Class set classID = className+'1', classname=classid where classid='java'
 /*
 khoa chinh da ton tai la khoa ngoai thi khong doi duoc
 chi update khoa ngoai cai da co cai trong khoa chinh
 */
 update Class set maxAmount=28 where classID = 'class01'
 update class set classID='class10' where classid='class01'
 update class set classID='class40' where classid = 'class04'
 update Student set classID='class021' where classid='class02'
 update Student set classID='class100' where classid='class01'