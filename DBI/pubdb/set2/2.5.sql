select stor_name
from stores
where stor_id not in
(
	select sa.stor_id
	from sales sa, stores st
	where sa.stor_id=st.stor_id
	and title_id = 'PC1035'
)
