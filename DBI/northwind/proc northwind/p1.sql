--1--
create proc p1
as
select lower( lastName + ' ' + firstName) [Full name],
TitleOfCourtesy
from Employees