use master
go
if exists(select name from sysdatabases where name='ws1')
drop Database ws1
go
Create Database ws1
go
use ws1
go

create table Class(
	ClassCode varchar(10) primary key,
	HeadTeacher varchar(30),
	Room varchar(30),
	TimeSLot varchar(30),
	CloseDate datetime
)

create table Subject(
	SubjectCode varchar(10) primary key,
	SubjectName varchar(40),
	WTest bit,
	PTest bit,
	WTest_per smallint,
	PTest_per int
)

create table Student(
	RollNo varchar(10) primary key,
	ClassCode varchar(10) foreign key references Class(ClassCode),
	FullName varchar(30),
	Male bit,
	BirthDate datetime,
	Address varchar(30),
	Province char(2),
	Email varchar(30) default null
)

create table Mark(
	RollNo varchar(10) foreign key references Student(RollNo),
	SubjectCode varchar(10) foreign key references Subject(SubjectCode),
	primary key(RollNo, SubjectCode),
	WMark float,
	PMark float,
	Mark float
)
go
insert into Class(ClassCode, HeadTeacher, Room, TimeSLot, CloseDate)
values('C0609M', 'Nguyen Trung', 'Class 2, Lab 2', '19:30 - 21:30',
'9/25/2008')

insert into Class(ClassCode, HeadTeacher, Room, TimeSLot, CloseDate)
values('C0611L', 'Phan Dang', 'Class 1, Lab 1', '17:30 - 19:30',
'10/21/2008')
insert into Class(ClassCode, HeadTeacher, Room, TimeSLot, CloseDate)
values('T0611H', 'Vu Tran', 'Class 2, Lab 2', '9:30 - 11:30',
'8/15/2007')

insert into Subject(SubjectCode, SubjectName, WTest, PTest,WTest_per, 
PTest_per) values('C', 'Elementary Programming with C', 1, 1, 40, 60)

insert into Subject(SubjectCode, SubjectName, WTest, PTest,WTest_per, 
PTest_per) values('CF', 'Computer Fundamentals', 1, 0, 100, 0)
insert into Subject(SubjectCode, SubjectName, WTest, PTest,WTest_per, 
PTest_per) values('DWMX', 'Web Page Designing with Dreamweaver MX', 
1,1,40,60)
insert into Subject(SubjectCode, SubjectName, WTest, PTest,WTest_per, 
PTest_per) values('HDJ', 'HTML,DHTML & JavaScript', 1, 1, 40, 60)
insert into Subject(SubjectCode, SubjectName, WTest, PTest,WTest_per, 
PTest_per) values('SQL1', 'SQL1', 1, 0, 100, 0)
insert into Subject(SubjectCode, SubjectName, WTest, PTest,WTest_per, 
PTest_per) values('SQL2', 'SQL2', 1, 1, 40, 60)

insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province) values ('A01', 'C0611L', 'Nguyen Hung', 1, 
'11/23/1982', '65 Hoang Hoa Tham', 'HN')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province, Email) values ('A02', 'C0611L', 'Thanh Trong', 1, 
'10/21/1983', '', 'HT', 'trong@fpt.com.vn')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province, Email) values ('A03', 'C0611L', 'Dinh Dung', 1, 
'10/19/1986', '6 Tran Phu', 'HN', 'dung@fpt.com.vn')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province) values ('A04', 'C0611L', 'Xuan Nam', 1, 
'1/20/1985', '3 Kim Ma', 'HN')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province) values ('A05', 'C0611L', 'Dinh Hieu', 1, 
'10/10/1984', '', 'HP')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province, Email) values ('A06', 'C0611L', 'Huong Thao', 0, 
'9/11/1986', '', 'ND', 'thao@yahoo.com')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province) values ('A07', 'C0611L', 'Thu Huong', 0, 
'12/22/1986', '', 'ND')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province) values ('B01', 'C0609M', 'Nguyen Hung', 1, 
'11/23/1982', '65 Hoang Hoa Tham', 'HN')
insert into Student(RollNo, ClassCode, FullName, Male, BirthDate,
Address, Province) values ('B02', 'C0609M', 'Thanh Binh', 1, 
'10/21/1983', '', 'HT')


insert into Mark(RollNo, SubjectCode, WMark, PMark, Mark)
values('A01', 'C', 20,10,14)

insert into Mark(RollNo, SubjectCode, WMark, PMark, Mark)
values('A01', 'CF', 20,0,8)

insert into Mark(RollNo, SubjectCode, WMark, PMark, Mark)
values('A01', 'HDJ', 18,12,14.4)


insert into Mark(RollNo, SubjectCode, WMark, PMark, Mark)
values('A02', 'C', 23,15,18.2)

insert into Mark(RollNo, SubjectCode, WMark, PMark, Mark)
values('A02', 'CF', 23,0,9.2)
select * from Student
select * from Mark
select * from Subject
select * from Class
go

ALTER TABLE student
ADD CONSTRAINT [FK__Student__ClassCo]
FOREIGN KEY (classcode)REFERENCES class(classcode)
ON DELETE SET NULL
GO
delete from Class where Headteacher = 'Phan Dang'

select count(*) as 'Total std has cf mark' from Mark where SubjectCode = 'CF'
go
select count(distinct subjectcode) as 'number of std has mark' from Mark
go
select subjectcode, count(subjectcode) as 'Total number' from Mark 
group by SubjectCode order by count(subjectcode) desc
go
select * from class where CloseDate < '20050101'
delete from class where CloseDate < '20050101'
go