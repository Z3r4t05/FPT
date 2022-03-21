create proc cau26
@units int
as
select p.ProductID, ProductName, su.SupplierID, CategoryID, sum(Quantity) as 'Total Orderd'
from Products p, Orders o, [Order Details] od, Suppliers su
where  p.ProductID = od.ProductID  and od.OrderID = o.OrderID and su.SupplierID = p.SupplierID
group by p.ProductID, ProductName, su.SupplierID, CategoryID
having sum(Quantity) >= @units
order by ProductID desc

exec cau26 1400