/*
This query text was retrieved from showplan XML, and may be truncated.
*/

use Northwind

select productid as 'ma san pham', productname as 'ten san pham',
unitprice as giasanpham, unitsinstock soluongtonkho, unitsonorder,
totalunits = unitsinstock + unitsonorder from products
select productid as 'ma san pham', productname as 'ten san pham',
unitprice as giasanpham, unitsinstock soluongtonkho, unitsonorder,
 unitsinstock + unitsonorder totalunits  from products
--
select * from Employees
select titleofcourtesy+firstname+' '+lastname as namecard from Employees
--top
select top 13* from Products
select top 10 percent * from Products
--distinct foreign key
select distinct categoryID from Products
--
select distinct titleofcourtesy from Employees
--
select 1+1
select GETDATE()
select MONTH(getdate())
select SQRT(121)
select 'asd'+'asdf'
--
select * from Products where ProductName = 'chai'
select * from Products where ProductName like 'Cha%'
select * from Products where ProductName like '%ing'
select * from Products where ProductName like '%ead%'
-- like _: thay the cho 1 va chi 1 ki tu
select * from Products where ProductName like 'Cha_' 
--bắt đầu bằng cha và sau đó có đúng 1 kí tự
select * from Products where ProductName like 'Cha__'
--2 kí tự sau cha

--special: giống regex
select * from Products where ProductName like '[ACG]%'
select * from Products where ProductName like '[A-G]%'
select * from Products where ProductName like '[A-GTx]%'
select * from Products where ProductName like '[A-GT-X]%'
select * from Products where ProductName like '[^A-GT-X]%'

--
select * from Products 
order by UnitPrice, UnitsInStock asc
--sum
select sum(UnitsInStock) from Products 
--count: number of unit
select count(UnitsInStock) as numberofunit from Products
--
select max(UnitsInStock) as maxvalueofunit from Products
select min(UnitsInStock) as minvalueofunit from Products
select avg(UnitsInStock) as avgvalueofunit from Products
--
--group by: nhom cac ham theo nhom cac 
--ban ghi cung gia tri
--foreign key, nulti value
select * from Products
select categoryid, sum(unitsinstock)
as totalvalueofunitbycategory
from Products
group by CategoryID

--cac truong co mat trong group by --> c
--co mat torng select
select SupplierID, sum(unitsinstock) 
as totalbysupplier

from Products
group by supplierID
--
select CategoryID, SupplierID,
sum(UnitsInStock) as totalvaludofunits
from Products
group by CategoryID,SupplierID
order by CategoryID
--
select SupplierID, CategoryID, 
sum(UnitsInStock) as totalvaludofunits
from Products
group by SupplierID,CategoryID
order by SupplierID
--
select titleofcourtesy, Count(TitleOfCourtesy) 
from Employees
group by TitleOfCourtesy
--
select MONTH(birthdate) , count(BirthDate)
from Employees
group by month(birthdate)
--

select * from Products where 
UnitPrice = (select max(UnitPrice) from Products)

select top 2 * from Products order by UnitPrice desc

--danh sach hang ban duoc -> pid co tren orderdetail
select distinct ProductID from [Order Details]
select * from Products
where ProductID in (select distinct ProductID from [Order Details])
select * from Products
where ProductID not in (select distinct ProductID from [Order Details])
--
select EmployeeID, FirstName, LastName from Employees
--danh sach cac quan ly --> id co trong report to
--id co trong reportto
--employee co id trong 1
select distinct reportsto from Employees

select EmployeeID, FirstName, LastName from Employees
where EmployeeID in (select distinct reportsto from Employees)

select a.CategoryID, a.CategoryName, count(b.CategoryID) 
from Categories as a join Products as b on a.CategoryID = b.CategoryID
group by a.CategoryID,a.CategoryName
order by count(b.CategoryID) desc
--category : count-amount = max

select a.CategoryID, a.CategoryName, count(b.CategoryID) 
from Categories as a join Products as b on a.CategoryID = b.CategoryID
group by a.CategoryID,a.CategoryName
having count(b.CategoryID)>=all(
	select count(b.CategoryID) from Categories
	as a join Products as b on a.CategoryID = b.CategoryID
	group by a.CategoryID
)

--
select a.CategoryID, a.CategoryName, count(b.CategoryID) 
from Categories as a join Products as b on a.CategoryID = b.CategoryID
group by a.CategoryID,a.CategoryName
having count(b.CategoryID)<=all(
	select count(b.CategoryID) from Categories
	as a join Products as b on a.CategoryID = b.CategoryID
	group by a.CategoryID
)