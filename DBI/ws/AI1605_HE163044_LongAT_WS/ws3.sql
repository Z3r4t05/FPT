use master
go
if exists(select name from sysdatabases where name='ws3')
drop Database ws3
go
Create Database ws3
go
use ws3
go

create table KhachHang(
	makh nvarchar(5) primary key,
	tenkh nvarchar(30) not null,
	diachi nvarchar(50),
	dt nvarchar(10) check (len(dt)>=7 and len(dt)<=10) default null,
	email nvarchar(30) default null)
create table SanPham(
	mavt nvarchar(5) primary key,
	tenvt nvarchar(30) not null,
	dvt nvarchar(20),
	giamua int check (giamua > 0),
	slton int check (slton >= 0))
create table HoaDon(
	mahd nvarchar(10) primary key,
	ngay datetime,
	makh nvarchar(5) foreign key references khachhang(makh),
	tongtg int default null)
create table ChiTietHoaDon(
	mahd nvarchar(10) foreign key references HoaDon(mahd),
	mavt nvarchar(5) foreign key references sanpham(mavt),
	sl int check (sl > 0),
	khuyenmai int, giaban int)

insert into SanPham values('VT01', 'XI MANG','BAO', 50000, 5000)
insert into SanPham values('VT02', 'CAT','KHOI', 45000, 50000)
insert into SanPham values('VT03', 'GACH ONG','VIEN', 120, 800000)
insert into SanPham values('VT04', 'GACH THE','VIEN', 110, 800000)
insert into SanPham values('VT05', 'DA LON','KHOI', 25000, 100000)
insert into SanPham values('VT06', 'DA NHO','KHOI', 33000, 100000)
Select * from sanpham

insert into KhachHang(makh,tenkh,diachi,dt,email)
values('KH01','NGUYEN THI BE','TAN BINH','8457895','bnt@yahoo.com')
insert into KhachHang(makh,tenkh,diachi,dt,email)
values('KH02', 'LE HOANG NAM', 'BINH CHANH', '9878987', 'namlehoang@abc.com.vn')
insert into KhachHang(makh,tenkh,diachi,dt)
values('KH03', 'TRAN THI CHIEU', 'TAN BINH', '8457895')
insert into KhachHang(makh,tenkh,diachi)
values('KH04','MAI THI QUE ANH', 'BINH CHANH')
insert into KhachHang(makh,tenkh,diachi,email)
values('KH05','LE VAN SANG','QUAN 10','sanglv@hcm.vnn.vn')
insert into KhachHang(makh,tenkh,diachi,dt)
values('KH06','TRAN HOANG KHAI','TAN BINH','8457897')
select * from KhachHang
insert into HoaDon(mahd,ngay,makh) values('HD001', '5/12/2000', 'KH01')
insert into HoaDon(mahd,ngay,makh) values('HD002', '5/25/2000', 'KH02')
insert into HoaDon(mahd,ngay,makh) values('HD003', '5/25/2000', 'KH01')
insert into HoaDon(mahd,ngay,makh) values('HD004', '5/25/2000', 'KH04')
insert into HoaDon(mahd,ngay,makh) values('HD005', '5/26/2000', 'KH04')
insert into HoaDon(mahd,ngay,makh) values('HD006', '6/2/2000', 'KH03')
insert into HoaDon(mahd,ngay,makh) values('HD007', '6/22/2000', 'KH04')
insert into HoaDon(mahd,ngay,makh) values('HD008', '6/25/2000', 'KH03')
insert into HoaDon(mahd,ngay,makh) values('HD009', '8/15/2000', 'KH04')
insert into HoaDon(mahd,ngay,makh) values('HD010', '9/30/2000', 'KH01')
select * from HoaDon
go

select * from KhachHang where diachi = 'Tan binh'
go

select * from sanpham where tenvt like '%gach%' and giamua > 110
select * from ho

