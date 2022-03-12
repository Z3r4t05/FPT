select title, ytd_sales, ord_date
from sales s join titles t 
on s.title_id = t.title_id
where s.ord_date =
(
	select MIN(ord_date) from sales
)
order by title
