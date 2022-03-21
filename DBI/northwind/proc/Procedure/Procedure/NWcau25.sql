create proc cau25
@units int
as
select p.ProductID, ProductName, sum(Quantity) as 'Total Orderd'
from Products p, Orders o, [Order Details] od
where p.ProductID = od.ProductID  and od.OrderID = o.OrderID
group by p.ProductID, ProductName
having sum(Quantity) >= @units

exec cau25 1200