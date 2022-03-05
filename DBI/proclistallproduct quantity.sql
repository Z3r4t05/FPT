create proc listAllProductQuantity1
@quantityfrom smallint,
@quantityto smallint,
@name nvarchar(40)
as select * from Products 
where UnitsInStock 
between @quantityfrom and @quantityto
or ProductName like @name
go
exec listAllProductQuantity1 20,60,'%an%'
go