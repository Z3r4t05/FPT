create proc cau23
as
select CompanyName, Phone
from Customers
where CompanyName like 'W%'
union all
select LastName + ' ' + FirstName, HomePhone
from Employees

exec cau23 