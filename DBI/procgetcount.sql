create proc getCount
@amount smallint,
@count int output as
select @count=count(UnitsInStock) from Products
where UnitsInStock=@amount

declare @getcount1 int;
exec getcount 0,
@count = @getcount1 OUTPUT;
print'the year to date sales figure for this
territory is ' + convert(varchar(100),@getcount1);
go