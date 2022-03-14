/*
proc to calculate the total amount of an order. 
the amount of each product is calculated below
total amount is the sum of all amount in order
*/
create proc TotalAmount 
@OrderID nvarchar(255),
@TotalAmount float output
as
begin
	select @TotalAmount = sum(Amount) from
	(select OrderID, ProductID, SalePrice*Quantity*(1-Discount) [Amount] from OrderDetails
	where OrderID = @OrderID) a
end


/*
declare @t float
exec TotalAmount = 'CA=2014_100006', @t output  
print @t
exec TotalAmount = 'CA=2014_100678', @t output
print @t
*/