
create proc cau36
@max int output
as
begin
select 	@max  = max(t.Delay) from (select e.EmployeeID, count(IIF(ShippedDate > RequiredDate, 1, null)) as Delay
from Employees e, Orders o
where e.EmployeeID = o.EmployeeID
group by e.EmployeeID) as t
end

declare @maxDate int
exec cau36  @max = @maxDate output

select 
	e.EmployeeID, LastName, FirstName,
	count(IIF(ShippedDate > RequiredDate, 1, null)) as 'Delayed Orders'
from Employees e, Orders o
where e.EmployeeID = o.EmployeeID	
group by e.EmployeeID, LastName, FirstName
having count(IIF(ShippedDate > RequiredDate, 1, null)) = @maxDate