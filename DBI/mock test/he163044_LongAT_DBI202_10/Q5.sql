--find all orders has the latest order date
select * from Orders
where OrderDate = (
	select max(OrderDate) from Orders
)