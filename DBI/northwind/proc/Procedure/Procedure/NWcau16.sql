create proc cau16
as
select LastName + ' ' + FirstName 'Full name', TitleOfCourtesy, Case when TitleOfCourtesy = 'Mr.' then 'Male' else IIF(TitleOfCourtesy = 'Mrs.' or TitleOfCourtesy = 'Ms.', 'Female', 'Unwnown') end as sex
from Employees where TitleOfCourtesy = 'Mr.' or TitleOfCourtesy ='Dr.' or TitleOfCourtesy = 'Ms.' or TitleOfCourtesy = 'Mrs.' order by case when TitleOfCourtesy = 'Mr.' then 1 when TitleOfCourtesy in ('Mrs.', 'Ms.') then 2 else 3 end asc, EmployeeID asc

exec cau16