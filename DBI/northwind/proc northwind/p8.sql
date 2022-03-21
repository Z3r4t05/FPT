create proc p8 as
--8
select ProductID, ProductName, UnitPrice, ReorderLevel, UnitsOnOrder
from Products where UnitsOnOrder between 60 and 100