create view TopAdvances(Author, Advances) as
select au_fname + ' ' + au_lname, advance
from authors a join titleauthor ta
on a.au_id = ta.au_id
join titles t 
on t.title_id = ta.title_id
where advance > 10000
go

