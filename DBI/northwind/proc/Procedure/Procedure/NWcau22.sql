create proc cau22
@datediff int
as
select Employees.EmployeeID, LastName, FirstName, OrderID, OrderDate, RequiredDate, ShippedDate
from Employees, Orders where Employees.EmployeeID = Orders.EmployeeID and ShippedDate - @datediff > RequiredDate order by EmployeeID
	
exec cau22 7