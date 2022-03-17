use master
if exists(select name from sysdatabases where name='QuanLy_WS')
	drop database QuanLy_WS
go
create database QuanLy_WS
go
use QuanLy_WS

-- CREATE TABLE
create table NHANVIEN (
	HONV nvarchar(20) not null,
	TENLOT nvarchar(20),
	TEN nvarchar(20) not null,
	MANV int not null primary key,
	NGSINH date not null,
	DCHI nvarchar(50) not null,
	PHAI nvarchar(10) not null,
	LUONG int not null,
	MA_NQL int foreign key references NHANVIEN(MANV),
	PHG tinyint
	)

create table PHONGBAN (
	TENPHG nvarchar(50) not null,
	MAPHG tinyint not null primary key,
	TRPHG int not null foreign key references NHANVIEN(MANV),
	NG_NHANCHUC date not null
	)

create table DIADIEM_PHG (
	MAPHG tinyint not null foreign key references PHONGBAN(MAPHG),
	DIADIEM nvarchar(30),
	primary key (MAPHG, DIADIEM)
	)

create table THANNHAN (
	MA_NVIEN int not null foreign key references NHANVIEN(MANV),
	TENTN nvarchar(20) not null,
	PHAI nvarchar(10) not null,
	NGSINH date not null,
	QUANHE nvarchar(20) not null,
	primary key (MA_NVIEN, TENTN)
	)

create table DEAN (
	TENDA nvarchar(50) not null,
	MADA tinyint not null primary key,
	DDIEM_DA nvarchar(30) not null,
	PHONG tinyint not null foreign key references PHONGBAN(MAPHG)
	)

create table PHANCONG (
	MA_NVIEN int not null foreign key references NHANVIEN(MANV),
	SODA tinyint not null foreign key references DEAN(MADA),
	THOIGIAN decimal(18, 1),
	primary key(MA_NVIEN, SODA)
	)

alter table NHANVIEN add constraint FK__NHANVIEN__PHG foreign key (PHG) references PHONGBAN(MAPHG)

-- INSERT DATA
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Dinh', 'Ba', 'Tien', 123456789, '01/09/1955', '731 Tran Hung Dao, Q1, TPHCM', 'Nam', 30000)
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Nguyen', 'Thanh', 'Tung', 333445555, '12/08/1945', '638 Nguyen Van Cu, Q5, TPHCM', 'Nam', 40000)
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Bui', 'Thuy', 'Vu', 999887777, '07/19/1968', '332 Nguyen Thai Hoc, Q1, TPHCM', 'Nam', 25000)
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Le', 'Thi', 'Nhan', 987654321, '06/20/1931', '291 Ho Van Hue, QPN, TPHCM', 'Nu', 43000)
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Nguyen', 'Manh', 'Hung', 666884444, '09/15/1952', '975 Ba Ria, Vung Tau', 'Nam', 38000)
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Tran', 'Thanh', 'Tam', 453453453, '07/31/1962', '543 Mai Thi Luu, Q1, TPHCM', 'Nam', 25000)
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Tran', 'Hong', 'Quan', 987987987, '03/29/1959', '980 Le Hong Phong, Q10, TPHCM', 'Nam', 25000)
insert into NHANVIEN (HONV, TENLOT, TEN, MANV, NGSINH, DCHI, PHAI, LUONG)
	values ('Vuong', 'Ngoc', 'Quyen', 888665555, '10/10/1927', '450 Trung Vuong, Ha Noi', 'Nu', 55000)

insert into PHONGBAN (TENPHG, MAPHG, TRPHG, NG_NHANCHUC) values ('Nghien cuu', 5, 333445555, '05/22/1978')
insert into PHONGBAN (TENPHG, MAPHG, TRPHG, NG_NHANCHUC) values ('Dieu hanh', 4, 999887777, '01/01/1985')
insert into PHONGBAN (TENPHG, MAPHG, TRPHG, NG_NHANCHUC) values ('Quan ly', 1, 888665555, '06/19/1971')

update NHANVIEN set MA_NQL = 333445555, PHG = 5 where MANV = 123456789
update NHANVIEN set MA_NQL = 888665555, PHG = 5 where MANV = 333445555
update NHANVIEN set MA_NQL = 987654321, PHG = 4 where MANV = 999887777
update NHANVIEN set MA_NQL = 888665555, PHG = 4 where MANV = 987654321
update NHANVIEN set MA_NQL = 333445555, PHG = 5 where MANV = 666884444
update NHANVIEN set MA_NQL = 333445555, PHG = 5 where MANV = 453453453
update NHANVIEN set MA_NQL = 987654321, PHG = 4 where MANV = 987987987
update NHANVIEN set PHG = 1 where MANV = 888665555

insert into DIADIEM_PHG (MAPHG, DIADIEM) values (1, 'TP HCM')
insert into DIADIEM_PHG (MAPHG, DIADIEM) values (4, 'HA NOI')
insert into DIADIEM_PHG (MAPHG, DIADIEM) values (5, 'VUNG TAU')
insert into DIADIEM_PHG (MAPHG, DIADIEM) values (5, 'NHA TRANG')
insert into DIADIEM_PHG (MAPHG, DIADIEM) values (5, 'TPHCM')

insert into THANNHAN (MA_NVIEN, TENTN, PHAI, NGSINH, QUANHE)
	values (333445555, 'Quang', 'Nu', '04/05/1976', 'Con gai')
insert into THANNHAN (MA_NVIEN, TENTN, PHAI, NGSINH, QUANHE)
	values (333445555, 'Khang', 'Nam', '10/25/1973', 'Con trai')
insert into THANNHAN (MA_NVIEN, TENTN, PHAI, NGSINH, QUANHE)
	values (333445555, 'Duong', 'Nu', '05/03/1948', 'Vo chong')
insert into THANNHAN (MA_NVIEN, TENTN, PHAI, NGSINH, QUANHE)
	values (987654321, 'Dang', 'Nam', '02/29/1932', 'Vo chong')
insert into THANNHAN (MA_NVIEN, TENTN, PHAI, NGSINH, QUANHE)
	values (123456789, 'Duy', 'Nam', '01/01/1978', 'Con trai')
insert into THANNHAN (MA_NVIEN, TENTN, PHAI, NGSINH, QUANHE)
	values (123456789, 'Chau', 'Nu', '12/31/1978', 'Con gai')

insert into DEAN (TENDA, MADA, DDIEM_DA, PHONG)	values ('San pham X', 1, 'VUNG TAU', 5)
insert into DEAN (TENDA, MADA, DDIEM_DA, PHONG)	values ('San pham Y', 2, 'NHA TRANG', 5)
insert into DEAN (TENDA, MADA, DDIEM_DA, PHONG)	values ('San pham Z', 3, 'TP HCM', 5)
insert into DEAN (TENDA, MADA, DDIEM_DA, PHONG)	values ('Tin hoc hoa', 10, 'HA NOI', 4)
insert into DEAN (TENDA, MADA, DDIEM_DA, PHONG)	values ('Cap quang', 20, 'TP HCM', 1)
insert into DEAN (TENDA, MADA, DDIEM_DA, PHONG)	values ('Dao tao', 30, 'HA NOI', 4)

insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN) values (123456789, 1, 32.5)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN) values (123456789, 2, 7.5)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (666884444, 3, 40.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (453453453, 1, 20.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (453453453, 2, 20.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (333445555, 3, 10.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (333445555, 10, 10.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (333445555, 20, 10.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (999887777, 30, 30.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (999887777, 10, 10.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (987987987, 10, 35.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (987987987, 30, 5.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (987654321, 30, 20.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (987654321, 20, 15.0)
insert into PHANCONG (MA_NVIEN, SODA, THOIGIAN)	values (888665555, 20, null)