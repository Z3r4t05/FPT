select stor_name from stores
where stor_id in 
(
	select stor_id from sales
	group by stor_id
	having count(*) <= 5
)