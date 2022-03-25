use master
go
if exists(select name from sysdatabases where name='SchoolManagementLongAT')
drop Database SchoolManagementLongAT
go
Create Database SchoolManagementLongAT
go
use SchoolManagementLongAT
go

create table dbo.Class (
	cID char(6) not null primary key,
	Name varchar(11),
	MaxCapacity tinyint,
	MinCapacity tinyint
)

create table dbo.Student (
	sID char(8) not null primary key,
	cID char(6) foreign key references Class(cID),
	Name varchar(25),
	Age tinyint,
	Gender bit,
)
--1--2
go
insert into Class values('C0912G', 'ACCP_C0912G', 25,3)
insert into Class values('A1001L','ACCP_A1001L',30,5)
insert into Class values('N0910H', 'ACNA_N0910H', 24,3)
insert into Class values('T0911K','ITT_C0911K',25,3)
go
insert into Student values('C0912G01', 'C0912G','ETO',24,1)
insert into Student values('C0912G02', 'C0912G','MESSI',24,1)
insert into Student values('C0912G03', 'C0912G','DECO',24,1)
insert into Student values('C0912G04', 'C0912G','Dayana Mendoza',24,0)
go
--3
create view dbo.StudentClass(sID, [Student Name], cID, [Class Name])
with schemabinding
as select s.sID,s.Name,c.cID,c.Name 
from dbo.Student s join dbo.Class c
on s.cID = c.cID
go

--4
create unique clustered index sID_cID  
on StudentClass(sID, cID)
go
--5
create proc insertClass
@cID char(6),
@name varchar(11),
@maxcapacity tinyint,
@mincapacity tinyint
as 
insert into class values(@cID, @name, @maxcapacity, @mincapacity)
go
--6
create proc insertStudent 
@sid char(8),
@cid char(6),
@name varchar(25),
@age tinyint,
@gender bit as
insert into student values(@sid, @cid, @name,
@age, @gender)
go
--7
create proc productUpdateMinCapacity
@n tinyint,
@m tinyint as
update class set MinCapacity = MinCapacity + @m
where MinCapacity <= @n
go
--8
create proc ShowCapacity
@num int
as select * from class where MinCapacity < @num
go
--9
create proc TestCapacity 
as select c.cID, count(sID) [Total Student]
from class c join student s
on c.cID = s.cID
group by c.cid, MinCapacity, MaxCapacity
having count(sID) between (MinCapacity) and MaxCapacity
go
--10
create proc TotalCapacity 
as 
select sum(a.[Total Student]) as [Total Capacity]
from (select c.cID, count(sID) [Total Student]
from class c join student s
on c.cID = s.cID
group by c.cid, MinCapacity, MaxCapacity
having count(sID) between (MinCapacity) and MaxCapacity) a
go
--11
create proc TransferStudent 
@sID char(8),
@cID char(6)
as 
begin 
if not exists(select * from student where sID = @sid) 
	begin
		print 'Not exist ' + @sid
		return -1
	end
if not exists(select cID from class where cID = @cid)
	begin
		print 'Not exist ' + @cid
		return -2
	end
update student set cid = @cID
where sID = @sID
end
go
--12
create proc deleteStudent 
@sid char(8)
as delete from Student where sID = @sid
go
--13
create trigger NoInsert on student for insert
as if(select age from inserted ) <= 0
begin
	print 'age must be positive'
	rollback transaction
end
go

--14
create trigger NoDel on student for delete
as if(
select count(sID) from class c join student s on
c.cID = s.cID where sID = (select sid from deleted)) 
< (select mincapacity from Class where cid = (
select cid from deleted))
begin
	print 'capacity reach minimum cannot delete'
	rollback transaction
end 
go
--15
create trigger updateClass on class
instead of update as
if(update(cid))
begin 
	alter table student drop 
	constraint [FK__Student__cID__267ABA7A]
	alter table student add
	constraint FK_Student_cID 
	foreign key (cid) references 
	class (cid) on update cascade
	update class set cid = (select cid from inserted)
	from class c join deleted d on 
	c.cID = d.cID
end
go
/*
exec TransferStudent 'C0912G01','A1001L'
select * from student
update class set cid = 'CxxxxG' where cID = 'C0912G'
select * from class
select * from Student
go
*/
