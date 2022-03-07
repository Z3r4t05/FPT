create proc procTopClass
@pClassCode varchar(10),
@pSubjectCode varchar(10),
@pRollNo varchar(10) out
as 
begin
	select top 1 @pRollNo = m.RollNo
	from Mark m join Student s
	on m.RollNo = s.RollNo
	where ClassCode = @pClassCode
	and SubjectCode = @pSubjectCode
	order by Mark desc
end

select  *
	from Mark m join Student s
	on m.RollNo = s.RollNo order by mark desc

declare @topStd varchar(10)
exec procTopClass 'C0611L', 'C', @topStd out
select FullName from Student where RollNo = @topStd