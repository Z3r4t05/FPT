select emp_id, lname,pub_name
from employee e join publishers p
on e.pub_id = p.pub_id
order by e.lname



