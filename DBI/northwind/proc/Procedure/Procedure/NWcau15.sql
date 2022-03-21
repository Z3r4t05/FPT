create proc cau15
as
select LastName + ' ' + FirstName 'Full name', TitleOfCourtesy, IIF(TitleOfCourtesy = 'Mr.' or TitleOfCourtesy = 'Dr.', 'M', 'F' )  sex
from Employees where TitleOfCourtesy = 'Mr.' or TitleOfCourtesy ='Dr.' or TitleOfCourtesy = 'Ms.' or TitleOfCourtesy = 'Mrs.' order by sex desc

exec cau15