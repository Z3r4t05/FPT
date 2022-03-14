
create table Students (
	[StudentID] int primary key,
	[Name] nvarchar(50),
	[Address] nvarchar(200),
	[Gender] char(1),
)

create table Teachers (
	TeacherID int primary key,
	[Name] nvarchar(50),
	[Address] nvarchar(200),
	Gender char(1),
)

create table Classes (
	ClassID int primary key,
	GroupID char(6),
	courseID char(6),
	NoCredits int,
	Semester char(10),
	[year] int,
	TeacherID int foreign key references Teachers(TeacherID)
)

create table Attend (
	Date date,
	Slot int,
	Attend bit,
	StudentID int foreign key references Students(StudentID),
	ClassID int foreign key references Classes(ClassID),
	primary key(Date,Slot,StudentID, ClassID)
)

