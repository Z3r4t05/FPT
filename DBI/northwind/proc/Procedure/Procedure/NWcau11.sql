create proc cau11
@from datetime, @to datetime
as
select Employees.EmployeeID, LastName, FirstName, HireDate, [Total Order] from Employees, (select EmployeeID, count(Orders.OrderID) 'Total Order'  from Orders where OrderDate >= @from and OrderDate <= @to group by EmployeeID) as totalOrder
where Employees.EmployeeID = totalOrder.EmployeeID

exec cau11 '01/01/1998', '07/31/1998'