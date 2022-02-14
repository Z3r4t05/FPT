use master
go
if exists(select name from sysdatabases where name='ws5')
drop Database ws5
go
Create Database ws5
go
use ws5
go

create table Khoa(
makhoa varchar(20) primary key,
tenkhoa nvarchar(40),
socbgd int)

create table Lop(
malop int identity(1,1) primary key,
tenlop nvarchar(30),
makhoa varchar(20) foreign key references khoa(makhoa))

create table SinhVien(
masv int identity(1,1) primary key,
hoten nvarchar(50),
nu bit not null,
ngaysinh datetime,
malop int foreign key references lop(malop),
hocbong int,
tinh nvarchar(20))
go
create table MonHoc(
	mamh int identity(1,1) primary key,
	tenmh nvarchar(30),
	sotiet int)
	go
create table ketqua(
	masv int foreign key references sinhvien(masv),
	mamh int foreign key references monhoc(mamh),
	primary key(masv, mamh),
	diemthi real)
	go

insert into khoa values('CNTT', 'Cong Nghe Thong Tin', 10)
insert into khoa values('QTKD', 'Quan Tri Kinh Doanh', 10)
insert into khoa values('NNA', 'Ngon Ngu Anh', 10)
select*from Khoa
go
insert into lop values( 'Artificial Intelligence','CNTT')
insert into lop values( 'Software Engineering', 'CNTT')
insert into lop values( 'English Language', 'NNA')
insert into lop values( 'Marketing', 'QTKD')
insert into lop values( 'Finance', 'QTKD')
insert into lop values( 'Graphic Design', 'CNTT')
select * from lop
go
insert into SinhVien(hoten, nu, ngaysinh, malop, hocbong, tinh)
values('Nguyen A', 1, '1/1/2000', 2, 1000000, 'HN')
insert into SinhVien(hoten, nu, ngaysinh, malop, hocbong, tinh)
values('Nguyen E', 0, '1/2/2000', 1, 0, 'HN')
insert into SinhVien(hoten, nu, ngaysinh, malop, hocbong, tinh)
values('Nguyen F', 1, '12/1/2000', 3, 700000, 'HN')
insert into SinhVien(hoten, nu, ngaysinh, malop, hocbong, tinh)
values('Nguyen G', 0, '1/31/2000', 5,600000, 'HN')
insert into SinhVien(hoten, nu, ngaysinh, malop, hocbong, tinh)
values('Nguyen H', 1, '3/5/2000', 6, 5000000, 'HN')
insert into SinhVien(hoten, nu, ngaysinh, malop, hocbong, tinh)
values('Nguyen J', 0, '7/2/2000', 4, 2000000, 'HN')
select * from SinhVien
go
insert into MonHoc values ( 'Database', 30)
insert into MonHoc values('Business English', 30)
insert into MonHoc values('Human Anatomy', 30)
insert into MonHoc values( 'Macroeconomic', 30)
select * from MonHoc 
go
insert into ketqua values(1,1,9.5)
insert into ketqua values(2,1,9)
insert into ketqua values(3,2,10)
insert into ketqua values(4,4,8)
insert into ketqua values(5,3,7)
insert into ketqua values(6,4,7)
select * from  ketqua
go