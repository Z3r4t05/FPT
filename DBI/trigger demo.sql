/*
drop trigger checkquantity
create trigger checkquantity
on [order details]
for insert
as if (
SElect quantity from inserted) < 2
begin 
print 'quantity of product >= 1'
rollback transaction
end
*/
/*
create trigger checkunitprice
on[order details]
for insert 
as if(select unitprice from inserted) <> (select unitprice
from Products where ProductID =
(select ProductID from inserted))
begin 
print'unitprice must equal proce of products'
rollback transaction
end

select * from Products
*/

/*
create trigger checkunitonorder
on products
for update
as 
if (select UnitsOnOrder from inserted) <=
(select unitsonorder from deleted)
begin 
print 'new unitonorder > old unitonorder'
rollback transaction
end
*/
/*
CREATE TRIGGER Check_EmployeeID
ON Employees
FOR UPDATE
AS
IF UPDATE(EmployeeID)
BEGIN
PRINT 'You cannot modify the ID of an employee'
ROLLBACK TRANSACTION
END
*/
drop trigger deleteregion
create trigger deleteregion
on region 
instead of delete
as begin
--delete foreign key
delete from EmployeeTerritories where TerritoryID 
in (select TerritoryID from Territories where RegionID in 
(select RegionID from deleted))
delete from Territories where RegionID in
(select RegionID from deleted)
--delete primary key
delete from region where regionID in
(select RegionID from deleted)
end
select * from region
select * from Territories

delete from Region where RegionID = 1