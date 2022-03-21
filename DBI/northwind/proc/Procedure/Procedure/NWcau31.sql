create proc cau31
@max int output
as
begin select @max = count(OrderID) from Employees e, Orders o  where e.EmployeeID = o.EmployeeID group by e.EmployeeID, LastName, FirstName, Title having count(OrderID) >= all
	(select a.Total_Orders from (select e.EmployeeID, LastName, FirstName, Title, count(OrderID) as Total_Orders from Employees e, Orders o 
	where e.EmployeeID = o.EmployeeID group by e.EmployeeID, LastName, FirstName, Title) a)
end

declare @maxOrder int
exec cau31 @max = @maxOrder output

select e.EmployeeID, LastName, FirstName, Title, count(OrderID) as Total_Orders
from Employees e, Orders o 
where e.EmployeeID = o.EmployeeID
group by e.EmployeeID, LastName, FirstName, Title
having count(OrderID) = @maxOrder