create proc p6 as
select CustomerID, CompanyName, phone, Address, City, Country
from customers where country = 'sweden'