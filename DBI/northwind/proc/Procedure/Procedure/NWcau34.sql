create proc cau34
@max int output
as
begin 
select @max =  max(UnitsOnOrder) from Products 

end

declare @maxUnit int
exec cau34 @max = @maxUnit output

select 
	ProductID, ProductName, s.SupplierID, CategoryID, UnitsOnOrder
from Products p join Suppliers s
on p.SupplierID = s.SupplierID
where UnitsOnOrder = @maxUnit
group by ProductID, ProductName, s.SupplierID, CategoryID, UnitsOnOrder