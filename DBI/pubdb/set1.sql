--1.1
select au_id from authors
where state <> 'CA'
--1.2
select au_lname from authors
where au_lname like 'D%'
--1.3
select state from authors
group by state
having count(au_id) = 2