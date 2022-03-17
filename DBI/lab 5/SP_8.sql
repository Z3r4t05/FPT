create proc sp_8
@top int
as
begin
select top (@top) * from NHANVIEN
order by LUONG desc
end



