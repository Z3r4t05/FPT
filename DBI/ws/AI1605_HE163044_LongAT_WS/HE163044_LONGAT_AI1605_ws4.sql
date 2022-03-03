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
  /*
  select masv, phai, hocbong, 
  iif(hocbong>80000, 'cao', 'thap')
 from dmsv
 */
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
 select makhoa,count(MaKhoa) as [so sv] from DMSV group by makhoa
 --35
select d.[so sv], d.[ma mon hoc],e.TenMH from
(select count(c.masv) as [so sv], c.mamon as [ma mon hoc]
from (select distinct b.MaSV as masv, b.MaMH as mamon, a.TenMH as ten
from DMMH as a inner join KetQua as b on a.MaMH = b.MaMH) c
group by c.mamon) d inner join DMMH as e on d.[ma mon hoc] = e.MaMH  
--
select tenmh 'ten mon hoc', count(distinct masv) 'ma sv'
from KetQua kq, dmmh mh
where kq.MaMH=mh.MaMH
group by tenmh
--36
select count(distinct MaMH) 'tong so mon hoc'
from KetQua
--37
select makhoa 'makhoa', sum(hocbong) 'tong hoc bong' from dmsv
group by makhoa
--38 hoc bong cao nhat moi khoa
select MaKhoa , max(hocbong) 'hoc bong cao nhat'
from DMSV
group by MaKhoa
--39 so sv nam nu moi khoa
select makhoa, 'so sv nam' = sum(case phai when 'nam' then 1 else 0 end),
'so sv nu' = sum(case phai when N'nữ' then 1 else 0 end)
from DMSV group by MaKhoa
--40 so luong sinh vien theo tung do tuoi
select year(GETDATE()) - year(ngaysinh) 'tuoi', count(masv) 'so sinh vien'
from DMSV group by year(Getdate()) - year(ngaysinh)
--41 nam sinh nao co 2 sv hoc tai truong
select year(ngaysinh) 'năm', count(masv)
from dmsv group by year(ngaysinh)
having count(masv)=2
--having thay the cho where 
--42 cho biet no nao co hon 2 sinh vien dng theo hoc tai truong
select NoiSinh, count(masv) 'so sinh vien'
from DMSV group by NoiSinh
having count(masv) >= 2
--43. Cho biết những môn nào có trên 3 sinh viên dự thi. 
select mamh 'Mã môn học',count(masv)'Số Sinh viên'		
from ketqua
group by mamh
having count(masv)>3
--44. Cho biết những sinh viên thi lại trên 2 lần. 
select masv,mamh,count(lanthi)'so lan thi lai'			
from ketqua
group by masv,mamh

having count(lanthi)>2
--45. Cho biết những sinh viên nam có điểm trung bình lần 1 trên 7.0 
select Hosv+' '+tensv 'Họ tên sinh viên',phai,lanthi,avg(Diem)'diem trung binh'
from ketqua kq,dmsv sv
where  kq.masv=sv.masv and lanthi=1 and phai=N'nam'
group by lanthi,phai, Hosv+' '+tensv 
having avg(Diem)>7.0
--46. Cho biết danh sách các sinh viên rớt trên 2 môn ở lần thi 1.
select masv 'Mã sinh viên',count(mamh)'Số môn rớt'
from ketqua
where lanthi=1 and diem<5
group by masv
having count(mamh)>=2

--47. Cho biết danh sách những khoa có nhiều hơn 2 sinh viên nam 
select makhoa 'Mã khoa','Số sinh viên nam'=count(masv)
from dmsv
where phai=N'Nam'
group by makhoa
having count(masv)>=2
--48 nhung khoa co sv dat hb tu 200k den 300k
select makhoa 'Mã khoa','Số sinh viên'=count(masv)
from dmsv
where hocbong between 200000 and 300000
group by makhoa
having count(masv)>2
--49 so sv dau va rot trong 1 lan thi
select tenmh 'Tên môn học','Số sinh viên Đậu'=sum(case when diem>=5 then 1 else 0 end ),'Số sinh viên Rớt'=sum(case when diem<5 then 1 else 0 end )
from ketqua kq,dmmh mh
where kq.mamh=mh.mamh and lanthi=1
group by tenmh
--50 so luong sv nam va nu tung khoa
select sv.MaKhoa,kh.TenKhoa,'Tổng sinh viên nam'=sum(case phai when  N'nam'then 1 else 0 end),
'Tổng sinh viên nữ'=sum(case phai when  N'nữ'then 1 else 0 end)
from dmsv sv join DMKhoa kh
on sv.MaKhoa = kh.MaKhoa
group by sv.makhoa,kh.TenKhoa

--subquery
--51 cho biet sv co max hoc bong
select hosv+' '+tensv 'Họ tên sinh viên',hocbong
from dmsv
where hocbong=(select max(hocbong) from dmsv)
--52 sinh vien co diem thi lan 1 mon co so du lieu cao nhat
select hosv+' '+tensv 'Họ tên sinh viên',tenmh 'Tên môn học',lanthi,diem
from ketqua kq inner join dmmh mh on kq.mamh=mh.mamh
inner join dmsv sv on sv.masv=kq.masv where lanthi=1 and tenmh=N'cơ sở dữ liệu' 
and diem=
(
	select max(diem) 
	from ketqua kq join dmmh mh 
	on kq.mamh=mh.mamh and tenmh=N'cơ sở dữ liệu' and lanthi=1
)
--53 sinh vien khoa anh van co tuoi lon nhat
select hosv+' '+tensv 'Họ tên sinh viên',ngaysinh 'Ngày sinh',makhoa 'Mã khoa'
from dmsv
where makhoa='av' and ngaysinh=(
				select min(ngaysinh)
				from dmsv
				where makhoa='av'
)
--54. Cho biết khoa nào có đông sinh viên nhất. 
select tenkhoa from dmsv sv inner join dmkhoa kh
on sv.MaKhoa = kh.MaKhoa
group by TenKhoa
having count(tenkhoa) >= all(select count(MaSV) from dmsv 
group by makhoa)
--55 cho biet khoa co so nu dong nhat
select tenkhoa from dmsv sv join DMKhoa kh
on sv.MaKhoa = kh.MaKhoa 
where phai = N'nữ'
group by TenKhoa
having count(TenKhoa) >= all(
select count(MaSV) from dmsv where phai = N'nữ'
group by MaKhoa
)
--56 cho biet mon nao co nhieu sinh vien rot lan 1 nhieu nhat
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