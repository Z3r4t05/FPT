--find all customer who order in a period of time and has shipped within 3 day
select c.ID, CustomerName, City,State from Customer c join Orders o
on c.ID = o.CustomerID
where OrderDate between '20171205' and '20171210'
and DATEDIFF(day, OrderDate, ShipDate) < 3
order by state asc, city desc