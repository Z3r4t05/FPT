create proc SP_3
@phg tinyint
as begin
select * from NHANVIEN 
where PHG = @phg
end


