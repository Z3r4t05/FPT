create proc cau4
@country nvarchar(15)
as
select CustomerID, CompanyName, ContactName, ContactTitle, Country from Customers where Country = @country

exec cau4 'UK'