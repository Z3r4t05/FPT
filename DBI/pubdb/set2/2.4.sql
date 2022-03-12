SELECT p.pub_id, pub_name, title
from publishers p LEFT OUTER JOIN titles t
ON  p.pub_id =  t.pub_id
order by p.pub_id
