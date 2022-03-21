create proc cau24
@idNumber int
as
select c.CustomerID, CompanyName, ContactName, ContactTitle 
from Customers c, Orders o
where
	c.CustomerID = o.CustomerID 
	and OrderID = @idNumber

exec cau24 10643