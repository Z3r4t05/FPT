/*
insert a category name sports into category
insert sub category of sports : tennis, football
*/
insert into Category(CategoryName) values ('Sports')
insert into SubCategory values ('Tennis', (select ID from Category where CategoryName = 'Sports'))
insert into SubCategory values ('Football', (select ID from Category where CategoryName = 'Sports'))
