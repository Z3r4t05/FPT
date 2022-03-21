create proc p5 
@country nvarchar(15)
as
select CustomerID, CompanyName, Address, City, Country
from Customers where country = @country

