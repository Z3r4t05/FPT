create proc cau39 
@top int
as
begin
with TempTable as
(select top (@top) p.ProductID, ProductName, sum(Quantity) as 'Total Orderd'
from Products p, Orders o, [Order Details] od, Suppliers su
where 
	p.ProductID = od.ProductID 
	and od.OrderID = o.OrderID
	and su.SupplierID = p.SupplierID
group by p.ProductID, ProductName, su.SupplierID, CategoryID
order by [Total Orderd] desc)

select * from TempTable
order by [Total Orderd]
end
exec cau39 5