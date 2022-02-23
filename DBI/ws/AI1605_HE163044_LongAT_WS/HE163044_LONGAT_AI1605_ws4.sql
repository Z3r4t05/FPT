/*=====================Create DataBase======================*/

use master
go
if exists(select name from sysdatabases where name='QuanLyDiemSV')
drop Database QuanLyDiemSV
go
Create Database QuanLyDiemSV
go

use QuanLyDiemSV
go


/*=============DANH MUC KHOA==============*/


Create table DMKhoa
(
MaKhoa char(2) primary key,
TenKhoa nvarchar(30) not null,
)


/*==============DANH MUC SINH VIEN============*/


Create table DMSV
(
MaSV char(3) not null primary key,
HoSV nvarchar(15) not null,
TenSV nvarchar(7) not null,
Phai nchar(7),
NgaySinh datetime not null,
NoiSinh nvarchar(20),
MaKhoa char(2),
HocBong float,

)




/*===================MON HOC========================*/



create table DMMH
(
MaMH char(2) not null,
TenMH nvarchar(25) not null,
SoTiet tinyint
Constraint DMMH_MaMH_pk primary key(MaMH)
)



/*=====================KET QUA===================*/

Create table KetQua
(
MaSV char(3) not null,
MaMH char(2) not null,
LanThi tinyint,
Diem decimal(4,2),
Constraint KetQua_MaSV_MaMH_LanThi_pk primary key(MaSV,MaMH,LanThi)
)

/*==========================TAO KHOA NGOAI==============================*/
Alter table dmsv
add Constraint DMKhoa_MaKhoa_fk foreign key(MaKhoa)
References DMKhoa (MaKhoa)
Alter table KetQua
add constraint KetQua_MaSV_fk foreign key(MaSV) references DMSV (MaSV),
constraint DMMH_MaMH_fk foreign key(MaMH) references DMMH (MaMH)


/*==================NHAP DU LIEU====================*/

/*==============NHAP DU LIEU DMMH=============*/
Insert into DMMH(MaMH,TenMH,SoTiet)
values('01',N'Cơ Sở Dữ Liệu',45)
Insert into DMMH(MaMH,TenMH,SoTiet)
values('02',N'Trí Tuệ Nhân Tạo',45)
Insert into DMMH(MaMH,TenMH,SoTiet)
values('03',N'Truyền Tin',45)
Insert into DMMH(MaMH,TenMH,SoTiet)
values('04',N'Đồ Họa',60)
Insert into DMMH(MaMH,TenMH,SoTiet)
values('05',N'Văn Phạm',60)

/*==============NHAP DU LIEU DMKHOA=============*/
Insert into DMKhoa(MaKhoa,TenKhoa)
values('AV',N'Anh Văn')
Insert into DMKhoa(MaKhoa,TenKhoa)
values('TH',N'Tin Học')
Insert into DMKhoa(MaKhoa,TenKhoa)
values('TR',N'Triết')
Insert into DMKhoa(MaKhoa,TenKhoa)
values('VL',N'Vật Lý')


/*==============NHAP DU LIEU DMSV=============*/


SET DATEFORMAT DMY
GO

Insert into DMSV
values('A01',N'Nguyễn Thị',N'Hải',N'Nữ','23/02/1990',N'Hà Nội','TH',130000)
Insert into DMSV(MaSV,HoSV,TenSV,Phai,NgaySinh,NoiSinh,MaKhoa,HocBong)
values('A02',N'Trần Văn',N'Chính',N'Nam','24/12/1992',N'Bình Định','VL',150000)
Insert into DMSV(MaSV,HoSV,TenSV,Phai,NgaySinh,NoiSinh,MaKhoa,HocBong)
values('A03',N'Lê Thu Bạch',N'Yến',N'Nữ','21/02/1990',N'TP Hồ Chí Minh','TH',170000)
Insert into DMSV(MaSV,HoSV,TenSV,Phai,NgaySinh,NoiSinh,MaKhoa,HocBong)
values('A04',N'Trần Anh',N'Tuấn',N'Nam','20/12/1990',N'Hà Nội','AV',80000)
Insert into DMSV(MaSV,HoSV,TenSV,Phai,NgaySinh,NoiSinh,MaKhoa,HocBong)
values('B01',N'Trần Thanh',N'Mai',N'Nữ','12/08/1991',N'Hải Phòng','TR',0)
Insert into DMSV(MaSV,HoSV,TenSV,Phai,NgaySinh,NoiSinh,MaKhoa,HocBong)
values('B02',N'Trần Thị Thu',N'Thủy',N'Nữ','02/01/1991',N'TP Hồ Chí Minh','AV',0)

/*==============NHAP DU LIEU BANG KET QUA=============*/

Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A01','01',1,3)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A01','01',2,6)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A01','02',2,6)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A01','03',1,5)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A02','01',1,4.5)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A02','01',2,7)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A02','03',1,10)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A02','05',1,9)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A03','01',1,2)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A03','01',2,5)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A03','03',1,2.5)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A03','03',2,4)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('A04','05',2,10)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('B01','01',1,7)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('B01','03',1,2.5)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('B01','03',2,5)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('B02','02',1,6)
Insert into KetQua(MaSV,MaMH,LanThi,Diem)
values('B02','04',1,10)

 select * from DMSV
 --9
 select masv as 'Mã sinh viên', hosv as 'Họ sinh viên', tensv as 'Tên', hocbong as 'Học bổng'
 from dmsv order by MaSV asc
 --10
 select masv as 'Mã sinh viên', hosv+' '+tensv as 'Họ tên sinh viên', Phai as 'Phái',
 NgaySinh as 'Ngày sinh'
 from dmsv order by phai asc
 --11
 select hosv+' '+tensv as 'Họ tên sinh viên',  NgaySinh as 'Ngày sinh', 
  hocbong as 'Học bổng' from dmsv order by NgaySinh asc, HocBong desc
  --12
  select MaMH as 'ma mon', TenMH as 'ten mon', SoTiet as 'so tiet' from DMMH
  where TenMH like 'T%'
  --13
  select hosv+' '+tensv as 'Họ tên sinh viên',
 NgaySinh as 'Ngày sinh', Phai as 'Phái'  from dmsv where TenSV like '%t' or HoSV like '%t'
 --14
 select MaKhoa as 'ma khoa', TenKhoa as 'ten khoa' from DMKhoa where TenKhoa like '_N%'
 --15
 select * from DMSV where hoSV like N'%Thị%'
 --16
 select masv as 'ma sinh vien', hosv+' '+tensv as 'Họ tên sinh viên', phai, hocbong from DMSV 
 where tensv like N'[a-m]%'
 --18
 select masv, hosv+' '+tensv as 'Họ tên sinh viên', makhoa, hocbong from dmsv 
 where hocbong > 100000 order by makhoa desc
 --17
 select  hosv+' '+tensv as 'Họ tên sinh viên', NgaySinh, NoiSinh, HocBong
 from dmsv where tensv like N'%[a-m]%' order by HoSV + ' ' + TenSV asc

 --19 
 select  hosv+' '+tensv as 'Họ tên sinh viên', makhoa, noisinh, hocbong from dmsv
 where noisinh = N'Hà Nội' and hocbong > 150000
 --20
 select MaSV, MaKhoa, Phai from dmsv where makhoa = 'vl' or makhoa = 'av'
 --21
 select masv, ngaysinh, NoiSinh, HocBong from dmsv
 where NgaySinh >= '19910101' and ngaysinh <= '19920605'
 --22
 select MaSV, NgaySinh, Phai, MaKhoa from dmsv
 where hocbong >= 80000 and hocbong <= 150000
 --23
 select MaMH, TenMH, SoTiet from DMMH where sotiet > 30 and sotiet < 45
 --24
 select masv, hosv, TenSV, MaKhoa, Phai from dmsv
 where phai = 'nam' and (MaKhoa = 'av' or makhoa = 'th')
 --25
 select * from dmsv where phai = N'Nữ' and TenSV like N'%n%'
 --26
 select HoSV, TenSV, NoiSinh, NgaySinh from dmsv  where noisinh = N'Hà Nội' and MONTH(NgaySinh) = 2 
 --27
 select hosv,TenSV, NgaySinh, Floor(datediff(day, NgaySinh, GETDATE())/365.25) as Tuoi from dmsv 
 where Floor(datediff(day, NgaySinh, GETDATE())/365.25) > 20
 /*
  select hosv,TenSV, NgaySinh, datediff(hour, NgaySinh, GETDATE())/8766
  AS AgeYearsIntTrunc, Floor(datediff(day, NgaySinh, GETDATE())/365.25) as Tuoi from dmsv 
 where Floor(datediff(day, NgaySinh, GETDATE())/365.25) > 20
 */
 --28
  select dmsv.hosv,dmsv.TenSV, Floor(datediff(day, NgaySinh, GETDATE())/365.25) as Tuoi, Dmkhoa.TenKhoa
  from dmsv inner join DMKhoa on dmsv.MaKhoa = dmkhoa.MaKhoa
 where Floor(datediff(day, NgaySinh, GETDATE())/365.25) > 20 and
 Floor(datediff(day, NgaySinh, GETDATE())/365.25) < 25
 --29
 select hosv,tensv,NgaySinh from  DMSV where year(ngaysinh) = 1990 and month(ngaysinh) between 1 and 3
 --30
 select masv, phai, hocbong,
 case when hocbong > 80000 then 'hoc bong cao'
 when hocbong = 0 then null
	else 'hocbong trung binh' 
	end as 'muc hoc bong'
 from dmsv
 --32
 select COUNT(MaSV) as [tong so sinh vien] from DMSV
 --33
 select * from dmsv
 select 
 count(case when phai = 'nam' then 1 end) as [sv nam],
 count(case when phai = N'nữ' then 1 end) as [sv nu],
 count(*) as total
 from dmsv 
 --34
 select * from DMSV
 /*
  select masv, phai, hocbong, 
  iif(hocbong>80000, 'cao', 'thap')
 from dmsv
 */

/*
Create Table SinhVien_KetQua
(
MaSV char(3) not null,
HoSV nvarchar(15) not null,
TenSV nvarchar(7) not null,
SoMonHoc tinyint
)

Insert Into SinhVien_KetQua
Select dmsv.MaSV,HoSV,TenSV,count(distinct MaMH)
From DMSV dmsv,KetQua kq
Where dmsv.MaSV=kq.MaSV
Group By dmsv.MaSV,HoSV,TenSV
go
alter table dmkhoa
add siso tinyint
go

update dmkhoa
set siso = (select count(masv) from dmsv where makhoa='av' group by(makhoa))
where makhoa='av'

update dmkhoa
set siso=(select count(masv)
from dmsv
where makhoa='TH'
group by(makhoa))
where makhoa='Th'


update dmkhoa
set siso=(select count(masv)
from dmsv
where makhoa='Tr'
group by(makhoa))
where makhoa='Tr'

update dmkhoa
set siso=(select count(masv)
from dmsv
where makhoa='vl'
group by(makhoa))
where makhoa='vl'
select*from DMMH
update DMMH set mamh = '45' where mamh='05'
select * from DMSV
delete DMSV where HocBong = 0
update DMSV set tensv = 'Kỳ', Phai = 'Nam' 
where MaSV = 'b01'
delete KetQua where LanThi = 2 and Diem < 5
*/