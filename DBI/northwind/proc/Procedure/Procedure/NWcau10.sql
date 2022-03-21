create proc cau10
@year int
as
select Employees.EmployeeID, LastName, FirstName, Title, Year = @year, [Total Order] from Employees, (select EmployeeID, count(Orders.OrderID) 'Total Order'  from Orders where year(OrderDate) = @year group by EmployeeID) as totalOrder
where Employees.EmployeeID = totalOrder.EmployeeID

exec cau10 1998