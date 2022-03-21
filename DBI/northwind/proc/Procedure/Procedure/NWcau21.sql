create proc cau21
@from datetime, @to datetime
as
select Products.CategoryID, CategoryName, Products.ProductID, ProductName, day(OrderDate) 'day', month(OrderDate) 'month', year(OrderDate) 'year', Revenue = Quantity * [Order Details].UnitPrice from Products,[Order Details], Categories, Orders 
where Products.ProductID = [Order Details].ProductID and Products.CategoryID = Categories.CategoryID and  [Order Details].OrderID = Orders.OrderID and OrderDate between @from and @to
order by CategoryID, ProductID
exec cau21 '07/01/1996', '07/05/1996'