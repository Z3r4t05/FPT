use Northwind
--1--
select lower( lastName + ' ' + firstName) [Full name],
TitleOfCourtesy
from Employees
--2--
select upper(lastName + ' ' + firstName) [full name]
from Employees
--3
select EmployeeID, LastName, FirstName, Title, city, Country
from Employees
where country = 'usa'
--4
select CustomerID,CompanyName,ContactName,ContactTitle,Country
from Customers where country = 'uk'
--5
select CustomerID, CompanyName, Address, City, Country
from Customers where country = 'mexico'
--6
select CustomerID, CompanyName, phone, Address, City, Country
from customers where country = 'sweden'
--7
select ProductID, ProductName, UnitPrice,UnitsInStock
from Products where UnitsInStock between 5 and 10
--8
select ProductID, ProductName, UnitPrice, ReorderLevel, UnitsOnOrder
from Products where UnitsOnOrder between 60 and 100
--9
select 
od.EmployeeID, 
ep.LastName, ep.FirstName, ep.Title, year(od.OrderDate),
count(od.EmployeeID) [total orders]
from orders od join Employees ep
on od.EmployeeID = ep.EmployeeID
where year(OrderDate) = 1996
group by od.EmployeeID, ep.LastName, ep.FirstName, ep.Title, year(od.OrderDate)

--10
select 
od.EmployeeID, 
ep.LastName, ep.FirstName, ep.City, ep.Country,
count(od.EmployeeID) [total orders]
from orders od join Employees ep on od.EmployeeID = ep.EmployeeID
where year(od.OrderDate) = 1998
group by od.EmployeeID, ep.lastname, ep.FirstName, ep.City, ep.Country
--11
select 
od.EmployeeID, 
ep.LastName, ep.FirstName, ep.HireDate,
count(od.EmployeeID) [total orders]
from orders od join Employees ep on od.EmployeeID = ep.EmployeeID
where od.OrderDate >= '19980101' and od.OrderDate <= '19980731'
group by od.EmployeeID, ep.lastname, ep.FirstName, ep.HireDate

--12
select 
od.EmployeeID, 
ep.LastName, ep.FirstName, ep.HireDate, ep.HomePhone,
count(od.EmployeeID) [total orders]
from orders od join Employees ep on od.EmployeeID = ep.EmployeeID
where od.OrderDate >= '19970101' and od.OrderDate <= '19970630'
group by od.EmployeeID, ep.lastname, ep.FirstName, ep.HireDate, ep.HomePhone
--13
select a.OrderID,a.orderday OrderDay, a.ordermonth OrderMonth, a.orderyear OrderYear,
a.Freight Freight, a.tax tax, 
cast(a.Freight*(1+cast(substring(a.tax, 0, CHARINDEX('%', a.tax)) as decimal)/100) as float)
as 'Freight with tax'
from ( 
select OrderID, day(OrderDate) orderday, month(orderdate) ordermonth, year(orderdate) orderyear,
orders.Freight, 
case when(Freight > 100) then format(0.1,'p0') else format(0.05,'p0') end as tax
from Orders
where OrderDate >= '19960801' and OrderDate <= '19960805'
) a
--14
select LastName + ' ' + FirstName as [Full name],
TitleOfCourtesy titleOfCourtesy,
case when TitleOfCourtesy = 'Mr.' then 'Male'
else 'Female' end as Sex
from Employees
where TitleOfCourtesy in ('Mr.', 'Mrs.', 'Ms.')
order by sex desc, EmployeeID asc

--15
select LastName + ' ' + FirstName as [full name],
TitleOfCourtesy titleofcourtesy, 
case when (TitleOfCourtesy in ('Mr.','Dr.')) then 'M'
else 'F' end as sex
from Employees

order by sex desc, EmployeeID asc
--16
select LastName + ' ' + FirstName as [full name],
TitleOfCourtesy titleofcourtesy, 
case when (TitleOfCourtesy = 'Mr.') then 'Male'
when (TitleOfCourtesy in ('Ms.','Mrs.')) then 'Female'
else 'Unknown' end as sex
from Employees

order by
case when titleofcourtesy = 'Mr.' then 1
when titleofcourtesy in ('Mrs.', 'Ms.') then 2
else 3 end asc, EmployeeID asc
--17
select LastName + ' ' + FirstName as [full name],
TitleOfCourtesy titleofcourtesy, 
case when (TitleOfCourtesy = 'Mr.') then 1
when (TitleOfCourtesy in ('Ms.','Mrs.')) then 0
else 2 end as sex
from Employees

order by
case when titleofcourtesy = 'Mr.' then 1
when titleofcourtesy in ('Mrs.', 'Ms.') then 2
else 3 end asc, EmployeeID asc
--18
select LastName + ' ' + FirstName as [full name],
TitleOfCourtesy titleofcourtesy, 
case when (TitleOfCourtesy = 'Mr.') then 'M'
when (TitleOfCourtesy in ('Ms.','Mrs.')) then 'F'
else 'N/A' end as sex
from Employees

order by
case when titleofcourtesy = 'Mr.' then 1
when titleofcourtesy in ('Mrs.', 'Ms.') then 2
else 3 end asc, EmployeeID asc
--21
select c.CategoryID, c.CategoryName, p.ProductID, p.ProductName,
Day(o.OrderDate) day,
month(o.OrderDate) month,
year(o.OrderDate) year,
quantity * p.UnitPrice as revenue
from Categories c join Products p 
on c.CategoryID = p.CategoryID
join [Order Details] od
on p.ProductID = od.ProductID
join Orders o
on o.OrderID = od.OrderID
where o.OrderDate >= '19960701' and o.OrderDate <= '19960705'
order by c.CategoryID asc, ProductID asc
--22
select e.EmployeeID, LastName, FirstName, orderid,
OrderDate, RequiredDate, ShippedDate
from Employees e join Orders o
on e.EmployeeID = o.EmployeeID
where datediff(DAY, RequiredDate, ShippedDate) >= 7
order by EmployeeID asc, OrderID asc
--23
/*
select CompanyName, Phone, ContactName
from Customers 
order by ContactName desc
where ContactName like 'W%'
*/
--24
select o.CustomerID, c.CompanyName, ContactName,ContactTitle
from Orders o join Customers c
on o.CustomerID = c.CustomerID
where OrderID = 10643
--25
select  ProductID, ProductName, UnitsOnOrder as [Total ordered] 
from Products where UnitsOnOrder >= 1200
order by UnitsOnOrder
--26
select ProductID, ProductName, SupplierID, CategoryID,
UnitsOnOrder as [Total ordered] 
from Products
where UnitsOnOrder > = 1400
--27

select c.CategoryID, c.CategoryName,
count(productID) as [Total products]
from Products p join Categories c
on p.CategoryID = c.CategoryID
group by c.CategoryID, c.CategoryName
having COUNT(ProductID) >= all(
	select count(ProductID)
	from Products group by CategoryID
)

--28
select c.CategoryID, c.CategoryName, 
count(productID) as [Total products]
from Products p right join  Categories c
on p.CategoryID = c.CategoryID
group by c.CategoryID, c.CategoryName
having COUNT(ProductID) <= all(
	select count(ProductID)
	from Products group by CategoryID
)
--29
select count(*) as [Total records] from Customers
union
select count(*) as [Total records] from Employees

select * from
(
select count(*) as [Total records customers] from Customers 
) a,
(
select count(*) as [Total records employees] from Employees 
) b

--30
select o.EmployeeID , e.LastName, e.FirstName, e.Title,
count(OrderID) as [Total orders]
from Orders o join Employees e
on o.EmployeeID = e.EmployeeID
group by o.EmployeeID,LastName, FirstName,Title
having count(OrderID) <= all(
	select count(OrderID) from Orders group by EmployeeID
)
--31
select o.EmployeeID , e.LastName, e.FirstName, e.Title,
count(OrderID) as [Total orders]
from Orders o join Employees e
on o.EmployeeID = e.EmployeeID
group by o.EmployeeID,LastName, FirstName,Title
having count(OrderID) >= all(
	select count(OrderID) from Orders group by EmployeeID
)

--32
select ProductID, ProductName, SupplierID, CategoryID,
UnitsInStock from Products
where UnitsInStock = (select max(UnitsInStock) from Products)
--33
select ProductID, ProductName, SupplierID, CategoryID,
UnitsInStock from Products
where UnitsInStock = (select min(UnitsInStock) from Products)
--34
select ProductID, ProductName, SupplierID, CategoryID,
UnitsOnOrder from Products
where UnitsOnOrder = (select max(UnitsOnOrder) from Products)
--35
select ProductID, ProductName, SupplierID, CategoryID,
ReorderLevel from Products
--36
 select o.EmployeeID, LastName, FirstName, count(OrderID) [Delayed Orders]
 from Orders o join Employees e on o.EmployeeID = e.EmployeeID
 where ShippedDate > RequiredDate
 group by o.EmployeeID, LastName, FirstName
 having count(o.EmployeeID) >= all(
	select count(*) from Orders where ShippedDate > RequiredDate
	group by EmployeeID
)

--37
 select o.EmployeeID, LastName, FirstName, count(OrderID) [Delayed Orders]
 from Orders o join Employees e on o.EmployeeID = e.EmployeeID
 where ShippedDate > RequiredDate
 group by o.EmployeeID, LastName, FirstName
 having count(o.EmployeeID) <= all(
	select count(*) from Orders where ShippedDate > RequiredDate
	group by EmployeeID
) and count(o.EmployeeID) > 0

--38
select top 3 productID, ProductName, UnitsOnOrder [Total Ordered]
from Products
order by UnitsOnOrder desc
--39
select * from (
select top 5 productID, ProductName, UnitsOnOrder [Total Ordered]
from Products
order by UnitsOnOrder desc ) a order by a.[Total Ordered] asc