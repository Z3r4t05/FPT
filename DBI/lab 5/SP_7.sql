create proc sp_7
as
begin
select HONV,TENLOT,TEN,a.PHG,LUONG from NHANVIEN a
inner join (select phg, avg(luong) as luongtb from NHANVIEN
group by phg) b
on a.PHG = b.phg
where luong > luongtb
end