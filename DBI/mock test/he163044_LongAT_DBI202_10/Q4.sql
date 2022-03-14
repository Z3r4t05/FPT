--find all subcategories has the number of product > 100
select sc.ID [SubCategoryID], sc.SubCategoryName, count(p.ID) [NumberOfProducts] from 
SubCategory sc join Product p
on sc.ID = p.SubCategoryID
group by  sc.ID, sc.SubCategoryName
having count(p.ID) > 100
order by count(p.ID) desc