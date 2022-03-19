select
PurchaseOrderID,SupplierID,
OrderDate,DeliveryMethod,ExpectedDeliveryDate
from PurchaseOrders
where DeliveryMethod like '%Air Freight%'
order by DeliveryMethod asc, OrderDate desc