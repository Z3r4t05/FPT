/*
dislay all customers who has the highest number of orderes */
select CustomerID, CustomerName, count(o.id) [NumberOfOrders]
from Orders o join Customer c
on o.CustomerID = C.ID
group by CustomerID, CustomerName
having count(o.id) = (
select max(b) from 
(select count(o.id) as [b] from Orders o join Customer c
on o.CustomerID = C.ID
group by CustomerID
) a)

