create proc cau35 
@max int output
as
begin select @max =  max(ReOrderLevel ) from Products 

end

declare @maxUnit int
exec cau35  @max = @maxUnit output

select 
	ProductID, ProductName, s.SupplierID, CategoryID, ReorderLevel as reorderlevel
from Products p join Suppliers s
on p.SupplierID = s.SupplierID
where ReorderLevel = @maxUnit
group by ProductID, ProductName, s.SupplierID, CategoryID, ReorderLevel