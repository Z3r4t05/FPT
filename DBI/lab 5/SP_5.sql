create proc sp_5
as select n.MANV,n.HONV,n.TENLOT, n.TEN, coalesce(sum(p.THOIGIAN),0) 
from  NHANVIEN n join PHANCONG p
on n.MANV = p.MA_NVIEN
group by n.HONV,n.TENLOT, n.TEN,n.MANV