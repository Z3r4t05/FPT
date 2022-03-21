create proc cau3
@country nvarchar(15)
as
select EmployeeID, LastName, FirstName, Title, City, Country from Employees where Country = @country

exec cau3 'USA'