use master
go
if exists(select name from sysdatabases where name='ws2')
drop Database ws2
go
Create Database ws2
go
use ws2
go
create table NhaCungCap(
	MaNhaCC nvarchar(10) primary key,
	TenNhaCC nvarchar(40),
	DiaChi nvarchar(60),
	Phone nvarchar(24),
	Fax nvarchar(24) default null,
	HomePage ntext
)

create table LoaiSanPham(
	MaLoaiSP nvarchar(10) primary key,
	TenLoaiSP nvarchar(15),
	MoTa ntext default null
)

create table SanPham(
	MaSP nvarchar(10) primary key,
	TenSP nvarchar(40),
	MaNhaCC nvarchar(10) foreign key references NhaCungCap(MaNhaCC),
	MaLoaiSP nvarchar(10) foreign key references LoaiSanPham(MaLoaiSP),
	DVT nvarchar(20),
	DonGia smallint,
	SoLuong smallint,
	SoLuongTT smallint,
	Discontinued bit
)

create table NhanVien(
	MaNV nvarchar(10) primary key,
	HoTen nvarchar(20),
	ChucDanh nvarchar(30),
	NgaySinh smalldatetime,
	NgayNhanViec smalldatetime,
	DiaChi nvarchar(50),
	Phone nvarchar(20) unique,
	Photo image,
	GhiChu ntext default null
)

create table KhachHang(
	MaKH nvarchar(10) primary key,
	TenKH nvarchar(30),
	DiaChi ntext,
	Phone nvarchar(24),
	Email nvarchar(30) unique
)

create table HoaDon(
	MaHD nvarchar(10) primary key,
	MaKH nvarchar(10) foreign key references KhachHang(MaKH),
	MaNV nvarchar(10) foreign key references NhanVien(MaNV),
	NgayLapHD smalldatetime,
	NgayNhanHang smalldatetime,
	Tien int,
	Thue real,
	TongSoTien int,
)

create table HoaDonChiTiet(
	MaHD nvarchar(10) foreign key references HoaDon(MaHD),
	MaSP nvarchar(10) foreign key references SanPham(MaSP),
	DonGia int,
	SoLuong smallint,
	GiamGia real,
	ThanhTien int
)
insert  into NhaCungCap(MaNhaCC, TenNhaCC, DiaChi, Phone,HomePage)
values('BAN', 'Cong ty Ban mai', '64 Hang Bun', '048286745', 'www.binhminh.com')
insert  into NhaCungCap(MaNhaCC, TenNhaCC, DiaChi, Phone,HomePage)
values('OCE', 'Cong ty Ocean', '26 Tran Quy Cap', '048924712', 'www.ocean.com')
insert  into NhaCungCap(MaNhaCC, TenNhaCC, DiaChi, Phone,HomePage)
values('RDO', 'Cong ty Rang Dong', '97 Hoang Hoa Tham', '048923711', 'www.rdong.com')
select * from NhaCungCap

insert into KhachHang values('01','Hoa Binh', 'Ha Noi', '0913567222', 'hb@fpt.vn')
insert into KhachHang values('02','Quang Long', 'Ha Noi', '048123445','qlong@Yahoo.com')
insert into KhachHang values('03', 'Nguyen Tuan', 'Hai Phong', '098467231', 'Tuan@yahoo.com')
select * from KhachHang

insert into NhanVien values('01', 'Lan Huong', 'Ban hang', '11/23/1980', '1/15/2004',
'Hoa Binh', '0912349865', null, null)
insert into NhanVien values('02', 'Bich Hue', 'Tiep thi', '12/31/1979', '1/22/2001',
'Quang Binh', '098768234', null,null)
insert into NhanVien values('03', 'Tung Chi', 'Ban hang', '8/9/1986', '1/14/2005',
'Hai Phong', '0985682534', null,null)
insert into NhanVien values('04', 'Kien Cuong', 'Thu kho', '9/19/1979', '1/22/2003',
'Ha Noi', '0912367299', null,null)
select * from NhanVien
insert into LoaiSanPham(MaLoaiSP,TenLoaiSP) values('DL','Dien lanh')
insert into LoaiSanPham(MaLoaiSP,TenLoaiSP) values('DT','Dien tu')
insert into LoaiSanPham(MaLoaiSP,TenLoaiSP) values('MP', 'Hang my pham')
insert into LoaiSanPham(MaLoaiSP,TenLoaiSP) values('TD','Hang Tieu Dung')
select * from LoaiSanPham
insert into SanPham values('001', 'Vo tuyen', 'BAN', 'DT', 'Chiec', 4000, 25,5,0)
insert into SanPham values('002', 'Radio', 'BAN', 'DT', 'Chiec', 200, 150, 20,0)
insert into SanPham values('003', 'CD Player', 'BAN', 'DT', 'Chiec', 2000, 45,10,0)
insert into SanPham values('004', 'Tu lanh', 'RDO', 'DL', 'Chiec', 6000, 22,5,0)
insert into SanPham values('005', 'May lam kem', 'RDO', 'DL', 'Chiec', 7000, 19,4,0)
insert into SanPham values('007', 'May dieu hoa', 'RDO', 'DL', 'Chiec', 9000, 38,7,0)
insert into SanPham values('008', 'Xa phong thom', 'OCE', 'TD', 'Banh', 6, 200,50,0)
insert into SanPham values('009', 'Duong', 'OCE','TD','Kg',9,550,50,0)
insert into SanPham values('010', 'Sua', 'OCE', 'TD','Hop',120,80,20,0)
insert into SanPham values('011', 'Mi miliket', 'OCE','TD','Thung', 200,56,8,0)
select * from SanPham

insert into HoaDon values('01','02','01','12/14/2007','12/15/2007',0,0.05,0)
insert into HoaDon values('02','01','01','1/19/2006','2/15/2006',0,0.05,0)
insert into HoaDon values('03','02','02','10/30/2006','1/1/2067',0,1,0)
select * from HoaDon

insert into HoaDonChiTiet values('03','003',0,3,0.02,0)
insert into HoaDonChiTiet values('03','007',0,2,0.01,0)
insert into HoaDonChiTiet values('03','011',0,10,0,0)
insert into HoaDonChiTiet values('02','010',0,11,0.04,0)
insert into HoaDonChiTiet values('02','002',0,2,0.01,0)
insert into HoaDonChiTiet values('01','004',0,1,0.05,0)
insert into HoaDonChiTiet values('01','009',0,15,0.04,0)
select * from HoaDonChiTiet
go