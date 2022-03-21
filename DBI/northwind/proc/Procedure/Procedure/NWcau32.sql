create proc cau32
@max int output
as
begin select  @max = max(UnitsInStock) from Products 
end

declare @maxUnit int
exec cau32 @max = @maxUnit output

select p.ProductID, ProductName, s.SupplierID, CategoryID, UnitsInStock
from Products p, Suppliers s
where p.SupplierID = s.SupplierID and UnitsInStock = @maxUnit