create proc cau8
@from smallint, @to smallint
as
select ProductID, ProductName, UnitPrice, ReorderLevel, UnitsOnOrder from Products where UnitsOnOrder between @from and @to

exec cau8 60, 100