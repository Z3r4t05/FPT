create proc cau27 
@max int output
as
select @max = max(a.TotalProducts)  from  (select CategoryName, count(distinct ProductID) as TotalProducts
											from Products p, Categories c where p.CategoryID = c.CategoryID
											group by CategoryName) a

declare @maxTotalProduct int 
exec cau27 @max = @maxTotalProduct output

select p.CategoryID, CategoryName, count(distinct ProductID) as 'Total products'
from Products p, Categories c
where p.CategoryID = c.CategoryID
group by p.CategoryID, CategoryName
having count( distinct ProductID) = @maxTotalProduct