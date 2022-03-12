create view MostReps (Publisher, totalRepresentatives) as
select pub_id, count(emp_id)
from employee 
group by pub_id
go
select pub_name, totalRepresentatives
from publishers p JOIN MostReps m
on p.pub_id = m.publisher
where totalRepresentatives =
(
	select Max(totalRepresentatives)
	from MostReps
)


