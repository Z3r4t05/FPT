--1
select (fname + ' ' + lname), pub_id
from employee
where pub_id = 0877
--2
select pub_name, pr_info, p.pub_id
from publishers p, pub_info i
where p.pub_id = i.pub_id
and p.pub_id = 0736
--3

select au_fname, au_lname, state
from authors
where state ='TN' or state='UT'
--4
select a1.au_fname, a1.au_lname, a1.city, a1.zip, a1.address
from authors a1, authors a2
where a1.au_id != a2.au_id
and  a1.city = a2.city
and a1.zip = a2.zip
and a1.address = a2.address
order by a1.address
--5
select pub_name
from publishers
where not state = 'DC'
--6
select t.title, t.pub_id, p.state
from titles t, publishers p
where t.pub_id = p.pub_id
and p.pub_id not in
( select pub_id
from publishers
where state = 'CA')
order by p.pub_id
--7
select stor_name,count(ord_num)
from sales s, stores t
where s.stor_id = t.stor_id
group by stor_name
having count( *) >= 5
--8
select stor_name,count(ord_num)
from sales s, stores t
where s.stor_id = t.stor_id
group by stor_name
having count( *) <= 2
--9
select stor_name,count(ord_num)
from sales s, stores t
where s.stor_id = t.stor_id
group by stor_name
having count( *) = 4
--10
select ord_num 
from sales
group by ord_num
having count(*) = 1 and max(title_id) = 'BU1111'
--11
select title
from titles
where (price=10.95  or price =7.00)
and not type in
( select t1.title
from titles t1, titles t2
where t1.type = t2.type
and t1.price = 10.95
and t2.price = 7.00)
--12
SELECT title_id, ord_num, qty
 FROM sales
WHERE qty IN
 (SELECT Max(qty)
  FROM sales)
--13

