create proc p2
as 
select upper(lastName + ' ' + firstName) [full name]
from Employees