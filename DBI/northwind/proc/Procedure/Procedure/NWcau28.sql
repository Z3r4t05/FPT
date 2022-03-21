create proc cau28 
as
select p.CategoryID, CategoryName, count(distinct ProductID) as 'Total products'
from Products p, Categories c
where p.CategoryID = c.CategoryID
group by p.CategoryID, CategoryName
having count( distinct ProductID) <= all
(select a.TotalProducts  from  (select CategoryName, count(distinct ProductID) as TotalProducts
								from Products p, Categories c where p.CategoryID = c.CategoryID
								group by CategoryName) a)

exec cau28 