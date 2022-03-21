create proc cau6
@country nvarchar(15)
as
select CustomerID, CompanyName, Phone, Address, City, Country from Customers where Country = @country

exec cau6 'Sweden'
