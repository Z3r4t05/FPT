create proc cau2
as
select upper(LastName) + ' ' + upper(FirstName) 'Full name' from Employees

exec cau2