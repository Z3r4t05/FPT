

select s.SupplierID, s.SupplierName, count(distinct b.PurchaseOrderID) as [number of purchase],
count(distinct st.SupplierTransactionID) as [number of trans],
count(distinct p.[ProductID]) as [number of prod]
 from
Suppliers s left outer join (select * from PurchaseOrders
where  year(OrderDate) = 2013 and month(OrderDate) = 1) b
on s.SupplierID = b.SupplierID

left join products p on p.SupplierID = s.SupplierID
left join (select * from SupplierTransactions where 
year(TransactionDate) = 2013 and month(TransactionDate) = 1)
st on st.SupplierID = s.SupplierID
group by s.SupplierID, s.SupplierName
order by [number of purchase] desc, s.SupplierName asc


