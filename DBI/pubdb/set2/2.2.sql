--2.2
select title from titles
where pubdate = (
	select max(pubdate) from titles
)