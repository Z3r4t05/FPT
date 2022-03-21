create proc p3 
@country nvarchar(15)
as
select EmployeeID, LastName, FirstName, Title, city, Country
from Employees
where country = @country

