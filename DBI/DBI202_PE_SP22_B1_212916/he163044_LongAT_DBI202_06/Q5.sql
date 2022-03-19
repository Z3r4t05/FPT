select sc.SupplierCategoryID, sc.SupplierCategoryName,
count(distinct s.SupplierID) [NumberOfSuppliers],
count(p.PurchaseOrderID) [NumberOfPurchaseOrders]
from 
PurchaseOrders p right join Suppliers s 
on p.SupplierID = s.SupplierID
right join SupplierCategories sc on
sc.SupplierCategoryID = s.SupplierCategoryID
group by sc.SupplierCategoryID, sc.SupplierCategoryName
order by NumberOfPurchaseOrders desc, SupplierCategoryName asc






