--1 What are the names of all the languages in the database (sorted alphabetically)
select name from language order by	name asc
/*
2. Return the full names (first and last) of 
actors with “SON” in their last name, ordered by their first 
name.
*/
select first_name + ' ' + last_name from actor
where last_name	like '%son%'
/*
3. Find all the addresses where the second address is not empty (i.e., contains some text), and return 
these second addresses sorted
*/
select address2 from address where address2 <> '' order by address2 asc
/*
4. Return the first and last names of actors who played in a film involving
a “Crocodile” and a “Shark”, 
along with the release year of the movie, sorted by the actors’ last names.
*/
select a.first_name, a.last_name, f.description, f.release_year
from film_actor fa 
join actor a on fa.actor_id = a.actor_id
join film f on fa.film_id = f.film_id
where description like '%crocodile%' 
and description like '%shark%'
order by a.last_name
/*
5. How many films involve a “Crocodile” and a “Shark”?*/
select count(*) as [Total films involve a "Crocodile" and a "Shark"] from film 
where description like '%crocodile%' 
and description like '%shark%'
/*
6. Find all the film categories in which there are between 55 and 65 films. Return the names of these 
categories and the number of films per category, sorted by the number of films.
*/
select c.name as category, COUNT(film_id) as [Number of films]
from film_category fc join category c
on fc.category_id = c.category_id
group by c.name
having count(film_id) between 55 and 65
/*
7. In how many film categories is the average difference between the film replacement cost and the 
rental rate larger than 17
*/
select count(*) from
(select c.name, avg(b.replacement_cost) as [avg cost], 
avg(b.rental_rate) as [avg rate] 
from film_category a inner join film b on a.film_id=b.film_id 
inner join category c on c.category_id=a.category_id 
group by  c.name
) a
where a.[avg cost] - a.[avg rate] > 17

select count(*) from
(select a.category_id, avg(b.replacement_cost - b.rental_rate) as  dif
from film_category a
inner join film b
on a.film_id=b.film_id
group by a.category_id ) a 
where dif > 17

/*
8. Find the address district(s) name(s) such that the minimal postal code in the district(s) is maximal
over all the districts. Make sure your query ignores empty postal codes and district names.
*/
select top 1 district from (
select district, min(postal_code) as postalcode from address
where district <> '' and postal_code <> ''
group by district
) a order by a.postalcode desc
/*
9. Find the names (first and last) of all the actors and costumers whose first name is the same as the 
first name of the actor with ID 8. Do not return the actor with ID 8 himself. Note that you cannot 
use the name of the actor with ID 8 as a constant (only the ID). There is more than one way to solve 
this question, but you need to provide only one solution
*/
(select customer_id as id, first_name, last_name
from customer
where first_name = (
select first_name from actor
where actor_id = 8))
union all
(select actor_id as id, first_name, last_name from actor
where actor_id <> 8 and
first_name = (select first_name from actor
where actor_id = 8))
/*
10.
*/