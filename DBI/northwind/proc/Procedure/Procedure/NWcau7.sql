create proc cau7
@from smallint, @to smallint
as
select ProductID, ProductName, UnitPrice, UnitsInStock from Products where UnitsInStock between @from and @to

exec cau7 5, 10