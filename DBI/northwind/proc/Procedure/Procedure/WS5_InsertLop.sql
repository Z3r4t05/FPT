create proc InsertLop
@TenLop varchar(50), @maKhoa int
as 
insert into Lop(TenLop, maKhoa)
values(@TenLop, @maKhoa)

exec InsertLop 'Ai1606', 4

select * from Lop