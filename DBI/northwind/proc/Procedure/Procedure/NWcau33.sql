create proc cau33
@min int output
as
begin select  @min = min(UnitsInStock) from Products 
end

declare @minUnit int
exec cau33 @min = @minUnit output

select 
	ProductID, ProductName, s.SupplierID, CategoryID, UnitsInStock
from Products p join Suppliers s
on p.SupplierID = s.SupplierID and UnitsInStock = @minUnit
group by ProductID, ProductName, s.SupplierID, CategoryID, UnitsInStock
