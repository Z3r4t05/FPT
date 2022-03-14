/*
create trigger for insert to display the infor of the row just inserted
*/
create trigger	InsertSubCategory on subCategory
for insert
as
select SubCategoryName, CategoryName from inserted i join Category c
on i.CategoryID = c.ID



