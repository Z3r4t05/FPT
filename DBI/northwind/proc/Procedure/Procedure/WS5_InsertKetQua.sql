create proc InsertKetQua
@MaSV int, @MaMH int ,@DiemThi float
as 
insert into KetQua(MaSV, MaMH, DiemThi)
values(@MaSv, @MaMH, @DiemThi)

exec InsertKetQua 1, 3, 10

select * from KetQua