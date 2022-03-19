select PurchaseOrderID, SupplierID,
OrderDate, DeliveryMethod, ExpectedDeliveryDate
from PurchaseOrders
where OrderDate between '20130515' and '20130531'
order by SupplierID asc