
--2
create view TopEmployees (id, first_name, last_name, job_level) as
select emp_id,fname,lname,job_lvl from employee
where job_lvl = 
(
	select max(job_lvl) from employee
)
go
select * from TopEmployees