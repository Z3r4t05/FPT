create procedure procStudentList
@pClassCode varchar(10) = null,
@pMark float = 0
as 
begin
	if(select count(*) from Student s join mark m
	on s.RollNo = m.RollNo 
	where @pClassCode = ClassCode) = 0
	begin
		print 'error'
		return
	end
	
	select ClassCode,m.RollNo, FullName, SubjectCode , PMark
	from Student s join mark m
	on s.RollNo = m.RollNo 
	where PMark >= @pMark and ClassCode = @pClassCode
	
end

exec procStudentList 'C0611L', 10
