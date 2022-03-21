create proc cau13
@from datetime, @to datetime
as
select OrderID, Orderday = day(OrderDate), OrderMonth = month(OrderDate), OrderYear = year(OrderDate), Freight, IIF(Freight >= 100, '10%', '5%') 'tax', IIF(Freight >= 100, Freight * 1.1, Freight * 1.05) 'Freight with tax'
from Orders where OrderDate between @from and @to

exec cau13 '08/01/1996', '08/05/1996'