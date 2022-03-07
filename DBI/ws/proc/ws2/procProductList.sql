create proc procProductList 
@pYear int,
@pMonth int,
@pMaLoaiHang nvarchar(10) output
as 
begin 
	select top 1 @pMaLoaiHang = a.MaLoaiSP from
		(select s.MaLoaiSP, sum(ct.DonGia * ct.SoLuong)  [tong tien]
		from HoaDon hd join HoaDonChiTiet ct
		on hd.MaHD = ct.MaHD
		join SanPham s 
		on s.MaSP = ct.MaSP
		join LoaiSanPham l 
		on l.MaLoaiSP = s.MaLoaiSP
		where year(hd.NgayLapHD) = @pYear 
		and month(hd.NgayLapHD) = @pMonth
		group by s.MaLoaiSP
		) a
	order by a.[tong tien] desc

end

declare @topSale nvarchar(10)
exec procProductList 2006,10, @pMaLoaiHang = @topSale output
print @topSale
select TenLoaiSP from LoaiSanPham where MaLoaiSP = @topSale
/*
select TenLoaiSP from LoaiSanPham where MaLoaiSP = @topSale
select month(NgayLapHD) as y from HoaDon where year(NgayLapHD) = 2006
select top 1 a.MaLoaiSP from
		(select s.MaLoaiSP, sum(ct.DonGia * ct.SoLuong)  [tong tien]
		from HoaDon hd join HoaDonChiTiet ct
		on hd.MaHD = ct.MaHD
		join SanPham s 
		on s.MaSP = ct.MaSP
		join LoaiSanPham l 
		on l.MaLoaiSP = s.MaLoaiSP
		where year(hd.NgayLapHD) = 2006 
		and month(hd.NgayLapHD) = 10
		group by s.MaLoaiSP
		) a
	order by a.[tong tien] desc

*/



