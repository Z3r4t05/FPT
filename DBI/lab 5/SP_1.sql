create proc SP_1
@salary int
as 
begin
	select * from NHANVIEN where LUONG > @salary 
end

exec SP_1 50000
