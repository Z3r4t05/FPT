create proc p7 as 
select ProductID, ProductName, UnitPrice,UnitsInStock
from Products where UnitsInStock between 5 and 10