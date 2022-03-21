create proc p4
@country nvarchar(15)
as
select CustomerID,CompanyName,ContactName,ContactTitle,Country
from Customers where country = @country
