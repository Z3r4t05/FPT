select s.SupplierID, s.SupplierName,
count(p.ProductID) [NumberOfProduct]
from
Products p join Suppliers s 
on p.SupplierID = s.SupplierID
group by s.SupplierID, s.SupplierName
having count(p.ProductID) >= 
all (select 
count(p.ProductID) 
from Products p join Suppliers s 
on p.SupplierID = s.SupplierID
group by s.SupplierID, s.SupplierName) 
