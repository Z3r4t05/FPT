create proc InsertKhoa
@TenKhoa varchar(50), @SoCBGD varchar(20)
as 
insert into Khoa(TenKhoa, SoCBGD)
values(@TenKhoa, @SoCBGD)

exec InsertKhoa 'KTPM', 4

select * from Khoa