create proc cau12
@from datetime, @to datetime
as
select Employees.EmployeeID, LastName, FirstName, HireDate, HomePhone, [Total Order] from Employees, (select EmployeeID, count(Orders.OrderID) 'Total Order'  from Orders where OrderDate >= @from and OrderDate <= @to group by EmployeeID) as totalOrder
where Employees.EmployeeID = totalOrder.EmployeeID

exec cau12 '01/01/1997', '06/30/1997'