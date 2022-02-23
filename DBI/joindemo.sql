use Northwind
/*
join: ket noi du lieu ban ghi
*/
--cross join ket hop giua cac bang k co ket noi
/*
a crossjoin b 
-> moi ban ghi ben a ket hop voi ban ghi ben b 
-> a co n , b co m -> n * m
*/
select * from Products
select * from Customers
select * from Products,Customers
/*
inner join (default la join) ket hop theo khoa ngoai voi khoa chinh
tao thanh ban ghi
khong co khoa ngoai tuong ung thi k co ban ghi
thu bang khong quan trong
neu moi quan he duoc dam bao thi so luong ban ghi 
bang so luong ben nhieu
*/
select * 
from Categories inner join Products 
on Categories.CategoryID=Products.CategoryID

--
select a.CategoryID, CategoryName,ProductID, ProductName, UnitPrice, UnitsInStock
from Categories as a join Products as b 
on a.CategoryID = b.CategoryID
--
select a.CategoryID, b.CategoryName, count(UnitsInStock)
from Products as a join Categories as b 
on a.CategoryID = b.CategoryID
group by a.CategoryID,b.CategoryName
--multi join
select a.OrderID,a.OrderDate,
c.FirstName + ' ' + c.LastName as employeename,
e.ProductID,e.ProductName,d.Quantity, d.UnitPrice,
subtotal = d.Quantity*d.UnitPrice
from Orders as a join Customers as b
on a.CustomerID = b.CustomerID
join Employees as c on a.EmployeeID= c.EmployeeID
join [Order Details] as d on a.OrderID = d.OrderID
join products as e on d.ProductID = e.ProductID
/*
left outer join = inner + neu gia tri khoa cua bang ben trai
khong co gia tri khoa tuong ung o bang ben phai thi ket hop thanh 
bang ghi moi, trong do gia tri bang ben phai bang null
*/
select a.CategoryID,CategoryName, ProductID,ProductName,
UnitPrice,UnitsInStock
from Categories as a
left outer join Products as b on a.CategoryID= b.CategoryID

select a.CategoryID,CategoryName, ProductID,ProductName,
UnitPrice,UnitsInStock
from Products as b left outer join Categories as a
 on a.CategoryID= b.CategoryID

 select a.CategoryID,CategoryName, ProductID,ProductName,
UnitPrice,UnitsInStock
from Categories as a
right outer join Products as b on a.CategoryID= b.CategoryID

select a.CategoryID,CategoryName, ProductID,ProductName,
UnitPrice,UnitsInStock
from Products as b right outer join Categories as a
 on a.CategoryID= b.CategoryID
 --full outer join = left + join
 select a.CategoryID,CategoryName, ProductID,ProductName,
UnitPrice,UnitsInStock
from Products as b full outer join Categories as a
 on a.CategoryID = b.CategoryID
