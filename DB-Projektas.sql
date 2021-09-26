CREATE TABLE users(
Id INt GENERATED ALWAYS AS IDENTITY NOT NULL,
Username varchar(50) UNIQUE NOT NULL,
Name varchar (50) NOT NULL,
Surname varchar(50)NOT NULL,
Pasword varchar(255) NOT NULL,
Is_admin boolean,
PRIMARY KEY (Id)
)
CREATE TABLE exam(
 Id NOT NULL,
ExamName char(50) UNIQUE NOT NULL,
CreateDate timestamp,
Tryes int,
PRIMARY KEY (ID)
)

CREATE TABLE questions(
Id int GENERATED ALWAYS AS IDENTITY NOT NULL ,
QuestionText char(255) NOT NULL,
ExamId int NOT NULL,
Answer1 char(50) NOT NULL,
Answer2 char(50)NOT NULL,
Answer3 char(50)NOT NULL,
CorrectAnswer int NOT NULL,
CorrectAnswerCount int ,
PRIMARY KEY (ID),
CONSTRAINT Question_pkey1 FOREIGN KEY (ExamId) REFERENCES exams(Id)
)

CREATE TABLE examinations(
Id int GENERATED ALWAYS AS IDENTITY NOT NULL ,
ExamId int NOT NULL,
UserId int NOT NULL,
TotalQuestion int,
CorrectAnswers int,
Grade DECIMAL(4,2) ,
StartDate Timestamp ,
EndDate Timestamp,
PRIMARY KEY (Id),
CONSTRAINT ExamId_examination_pkey FOREIGN KEY (ExamId) REFERENCES exams(Id),
CONSTRAINT UserId_examination_pkey FOREIGN KEY (UserId) REFERENCES users(Id)
)

ALTER TABLE question ADD CONSTRAINT Pk_Question PRIMARY KEY (Id);

SELECT e."name" ,to_char(AVG (e2.grade),'999999D99')AS Grade_AVG FROM exams e 
JOIN examinations e2 ON e.id =e2.examid 
GROUP BY e."name" 
ORDER BY "name" 

CREATE INDEX exam_name_asc ON exams(name asc);
