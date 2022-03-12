select pub_name from publishers
where pub_id =
(
	select pub_id as TotalReps
	from employee
	group by pub_id
	having count(*) = 1 
	and Max(fname) = 'Rita' and Max(lname) = 'Muller'

)