create proc cau29 
@count int output
as
begin
	select @count = count(a.ID) from  (select customerID as ID
										from Customers union select cast(EmployeeID as nvarchar(20)) from Employees ) a
end

declare @countID int
exec cau29 @count = @countID output
select @countID as 'Total record'