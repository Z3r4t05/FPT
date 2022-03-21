create proc InsertMonHoc
@TenMH varchar(50), @SoTiet int
as 
insert into MonHoc(TenMH, SoTiet)
values(@TenMH, @SoTiet)

exec InsertMonHoc 'DBI202', 30

select * from MonHoc