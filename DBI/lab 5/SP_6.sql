create proc sp_6
as 
select MAPHG, TENPHG, count(*) from PHONGBAN p join NHANVIEN  n
on p.MAPHG = n.PHG
group by MAPHG, TENPHG