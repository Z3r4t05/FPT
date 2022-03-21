create proc cau14
as
select LastName + ' ' + FirstName 'Full name', TitleOfCourtesy, IIF(TitleOfCourtesy = 'Mr.', 'Male', 'Female' ) sex
from Employees where TitleOfCourtesy = 'Mr.' or TitleOfCourtesy = 'Ms.' or TitleOfCourtesy = 'Mrs.' order by sex desc

exec cau14