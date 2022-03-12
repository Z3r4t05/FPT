select ord_num
from sales
where (title_id = 'TC3218' or title_id = 'TC4203')
and ord_num not in
(
	--self join to check if the order contains both titles
	select s1.ord_num
	from sales s1 join sales s2
	on s1.stor_id = s2.stor_id
	and s1.ord_num = s2.ord_num
	and s1.title_id = 'TC3218'
	and s2.title_id = 'TC4203'

)
