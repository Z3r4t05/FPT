create proc cau1
as
select LastName + ' ' + FirstName 'Full name', TitleOfCourtesy from Employees

exec cau1