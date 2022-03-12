create view pubtitle as
select pub_name,title
from publishers p join titles t
on p.pub_id = t.pub_id
go
select * from pubtitle
