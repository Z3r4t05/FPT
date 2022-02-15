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

