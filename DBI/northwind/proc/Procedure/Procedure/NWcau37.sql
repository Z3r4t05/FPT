create proc cau37
@min int output
as
begin
	select 
		@min  = min(t.Delay)
	from
		(select e.EmployeeID, count(IIF(ShippedDate > RequiredDate, 1, null)) as Delay
		from Employees e, Orders o
	where e.EmployeeID = o.EmployeeID
	group by e.EmployeeID) as t

end

declare @minDate int
exec cau37  @min = @minDate output

select 
	e.EmployeeID, LastName, FirstName,
	count(IIF(ShippedDate > RequiredDate, 1, null)) as 'Delayed Orders'
from Employees e, Orders o
where e.EmployeeID = o.EmployeeID	
group by e.EmployeeID, LastName, FirstName
having count(IIF(ShippedDate > RequiredDate, 1, null)) = @minDate

