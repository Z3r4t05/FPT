--2.1
select title from titles
where price = (
	select max(price) from titles
)




