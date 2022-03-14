/*
5 subcategories have the highest number of different products
and 5 categories have the smalles number of different products
*/
select SubCategoryID, NumberOfProducts from
(select top 5 SubCategoryID, count(ID) [NumberOfProducts] from Product
group by SubCategoryID
order by count(ID) desc) a
union all
select SubCategoryID, NumberOfProducts from
(select top 5 SubCategoryID, count(ID) [NumberOfProducts] from Product
group by SubCategoryID
order by count(ID) asc) b order by NumberOfProducts desc