create proc InsertSinhVien
@HoTen nvarchar(50), @Nu bit, @NgaySinh datetime, @MaLop int, @HocBong int, @Tinh varchar(20)
as 
insert into SinhVien(HoTen, Nu, NgaySinh, MaLop, HocBong, Tinh)
values(@HoTen, @Nu, @NgaySinh, @MaLop, @HocBong, @Tinh)

exec InsertSinhVien 'Vu thuy Linh', 1, '08/13/2002', 4, 200000000, 'Ha Noi'

select * from SinhVien