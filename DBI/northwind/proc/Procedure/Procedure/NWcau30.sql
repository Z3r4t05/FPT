create proc cau30
@min int output
as
begin
	select @min = count(OrderID) from Employees e, Orders o  where e.EmployeeID = o.EmployeeID
	group by e.EmployeeID, LastName, FirstName, Title
	having count(OrderID) <= all (select a.Total_Orders from (select e.EmployeeID, LastName, FirstName, Title, count(OrderID) as Total_Orders
															from Employees e, Orders o  where e.EmployeeID = o.EmployeeID
															group by e.EmployeeID, LastName, FirstName, Title) a)
end

declare @minOrder int
exec cau30 @min = @minOrder output

select e.EmployeeID, LastName, FirstName, Title, count(OrderID) as Total_Orders
from Employees e, Orders o 
where e.EmployeeID = o.EmployeeID
group by e.EmployeeID, LastName, FirstName, Title
having count(OrderID) = @minOrder