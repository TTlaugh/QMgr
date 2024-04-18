DROP DATABASE IF EXISTS QuizzServer;
CREATE DATABASE IF NOT EXISTS QuizzServer;

USE QuizzServer;

CREATE TABLE Person (
    PersonID VARCHAR(20) PRIMARY KEY NOT NULL,
    FirstName VARCHAR(40) NOT NULL,
    LastName VARCHAR(10) NOT NULL,
    Email VARCHAR(70),
    Phone VARCHAR(15) NOT NULL,
    INDEX FIRSTNAME (FirstName ASC),
    INDEX LASTNAME (LastName ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Students (
    StudentID VARCHAR(15) PRIMARY KEY NOT NULL,
    PersonID VARCHAR(20) NOT NULL,
    CONSTRAINT FK_Student_PersonID FOREIGN KEY (PersonID) REFERENCES Person(PersonID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Teachers (
    TeacherID VARCHAR(15) PRIMARY KEY NOT NULL,
    PersonID VARCHAR(20) NOT NULL,
    CONSTRAINT FK_Teacher_PersonID FOREIGN KEY (PersonID) REFERENCES Person(PersonID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Accounts (
    PersonID VARCHAR(20) PRIMARY KEY NOT NULL,
    Password VARCHAR(30) NOT NULL,
    CONSTRAINT FK_Accounts_PersonID FOREIGN KEY (PersonID) REFERENCES Person(PersonID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE SGroups (
    SGroupID VARCHAR(30) PRIMARY KEY NOT NULL,
    TeacherID VARCHAR(15) NOT NULL,
    SGroupName VARCHAR(20) NOT NULL,
    CONSTRAINT FK_SGroup_TeacherID FOREIGN KEY (TeacherID) REFERENCES Teachers(TeacherID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE SGroupStudents (
    SGroupID VARCHAR(30) NOT NULL,
    StudentID VARCHAR(15) NOT NULL,
    PRIMARY KEY(SGroupID, StudentID),
    CONSTRAINT FK_SGroupID FOREIGN KEY (SGroupID) REFERENCES SGroups(SGroupID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_SGroup_StudentID FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Subjects (
    SubjectID VARCHAR(30) PRIMARY KEY NOT NULL,
    TeacherID VARCHAR(15) NOT NULL,
    SubjectName VARCHAR(100) NOT NULL,
    CONSTRAINT FK_Subject_Subject_TeacherID FOREIGN KEY (TeacherID) REFERENCES Teachers(TeacherID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Questions (
    QuestionID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubjectID VARCHAR(30) NOT NULL,
    Chapter INT NOT NULL,
    Difficulty INT NOT NULL,
    Content VARCHAR(1000) NOT NULL,
    Answer1 VARCHAR(100),
    Answer2 VARCHAR(100),
    Answer3 VARCHAR(100),
    Answer4 VARCHAR(100),
    CorrectAnswers JSON,
    INDEX CHAPTER (Chapter ASC),
    INDEX DIFFICULTY (Difficulty ASC),
    CONSTRAINT FK_Question_SubjectID FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Exams (
    ExamID DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,
    SubjectID VARCHAR(30) NOT NULL,
    StartDateTime DATETIME NOT NULL,
    TimeLimit INT NOT NULL,
    MaxScore DECIMAL(5, 2),
    Name VARCHAR(100),
    Description VARCHAR(500),
    isShuffled BOOLEAN DEFAULT FALSE,
    QuestionIDs JSON NOT NULL,
    CONSTRAINT FK_Exam_SubjectID FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Submissions (
    ExamID DATETIME NOT NULL,
    StudentID VARCHAR(15) NOT NULL,
    TimeTaken INT NOT NULL,
    Score DECIMAL(5, 2) NOT NULL,
    AnswerSelecteds JSON NOT NULL,
    PRIMARY KEY(ExamID, StudentID),
    CONSTRAINT FK_ExamID FOREIGN KEY (ExamID) REFERENCES Exams(ExamID) ON DELETE CASCADE,
    CONSTRAINT FK_Submission_StudentID FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO Person VALUES
('TCT001', 'Lê Thị', 'Hòa', 'hoa123@gmail.com ', '0911111110'),
('TCT006', 'Nguyễn Văn', 'Luận', 'luan1@gmail.com', '0922222220'),
('STS001', 'Trần Hoàng', 'Văn', 'van2@gmail.com', '0933333330'),
('TCT007', 'Lê Văn', 'Luân', 'luan2@gmail.com', '0944444440'),
('STS002', 'Nguyễn Lê', 'Lan', 'lan@gmail.com', '0955555550'),
('TCT002', 'Nguyễn Hoàng', 'Anh', 'anh@gmail.com', '0966666660'),
('STS004', 'Bùi Hoàng', 'Lam', 'lam@gmail.com', '0977777770'),
('STS005', 'Mai Ánh', 'Tuyết ', 'tuyet@gmail.com', '0988888880'),
('STS003', 'Hoàng Trần Văn', 'Đức', 'duc@gmail.com', '0999999990'),
('TCT009', 'Nguyễn Trung', 'Tín', 'tin@gmail.com', '0391111110'),
('TCT004', 'Nguyễn Văn', 'Thuận ', 'thuan@gmail.com', '0392222222'),
('STS010', 'Nguyễn Thị', 'Nhi', 'nhi@gmail.com', '0393333333'),
('STS008', 'Nguyễn Minh', 'Nhật', 'nhat@gmail.com', '0394444444'),
('STS007', 'Nguyễn Thu', 'Minh', 'minh@gmail.com', '0395555555'),
('TCT003', 'Trần Duy', 'Phương', 'phuong@gmail.com ', '0395555555'),
('TCT008', 'Mai Trúc', 'Lan', 'lan1@gmail.com', '0396666666'),
('TCT010', 'Mai Bích', 'Ngọc', 'ngoc@gmail.com', '0397777777'),
('STS009', 'Lê Như', 'Minh', 'minh1@gmail.com', '0398888888'),
('STS006', 'Lê Thị Bích', 'Tuyền ', 'tuyen@gmail.com', '0399999999'),
('TCT005', 'Hoàng Văn', 'Thụ', 'thu@gmail.com', '0339999999');

INSERT INTO Students VALUES
('S001', 'STS001'),
('S002', 'STS002'),
('S003', 'STS003'),
('S004', 'STS004'),
('S005', 'STS005'),
('S006', 'STS006'),
('S007', 'STS007'),
('S008', 'STS008'),
('S009', 'STS009'),
('S010', 'STS010');

INSERT INTO Teachers VALUES
('T001', 'TCT001'),
('T002', 'TCT002'),
('T003', 'TCT003'),
('T004', 'TCT004'),
('T005', 'TCT005'),
('T006', 'TCT006'),
('T007', 'TCT007'),
('T008', 'TCT008'),
('T009', 'TCT009'),
('T010', 'TCT010');

INSERT INTO Accounts VALUES
('TCT001', 'tttt'),
('TCT002', 'tttt'),
('TCT003', 'tttt'),
('TCT004', 'tttt'),
('TCT005', 'tttt'),
('TCT006', 'tttt'),
('TCT007', 'tttt'),
('TCT008', 'tttt'),
('TCT009', 'tttt'),
('TCT010', 'tttt');

INSERT INTO SGroups VALUES
('T00101', 'T001', 'GROUP01'),
('T00202', 'T002', 'GROUP02'),
('T00303', 'T003', 'GROUP03'),
('T00404', 'T004', 'GROUP04'),
('T00505', 'T005', 'GROUP05'),
('T00606', 'T006', 'GROUP06'),
('T00707', 'T007', 'GROUP07'),
('T00808', 'T008', 'GROUP08'),
('T00909', 'T009', 'GROUP09'),
('T01010', 'T010', 'GROUP10');

INSERT INTO SGroupStudents VALUES
('T00101', 'S001'),
('T00101', 'S002'),
('T00101', 'S003'),
('T00101', 'S004'),
('T00505', 'S005'),
('T00505', 'S006'),
('T00505', 'S007'),
('T00707', 'S008'),
('T00909', 'S009'),
('T01010', 'S010');

INSERT INTO Subjects VALUES
('T001S01', 'T001', 'Sinh'),
('T002S01', 'T002', 'Sử'),
('T003S01', 'T003', 'Toán '),
('T004S01', 'T004', 'Văn'),
('T005S01', 'T005', 'Toán '),
('T006S01', 'T006', 'Văn'),
('T007S01', 'T007', 'Toán '),
('T008S01', 'T008', 'Địa Lí'),
('T009S01', 'T009', 'Hóa Học'),
('T010S01', 'T010', 'Vật Lí ');

INSERT INTO Questions VALUES
(1,  'T004S01', 1, 1, 'Sáng tác của Nguyễn Ái Quốc, Hồ Chí Minh không bao gồm những thể loại nào trong các thể loại sau đây:', 'Văn chính luận', 'Miêu tả', 'Truyện', 'thơ', '[2]'),
(2,  'T004S01', 1, 2, 'Tuyên ngôn độc lập của Hồ Chí Minh được viết theo thể loại nào sau đây:', 'Văn chính luận', 'kí', 'thơ', 'Truyện dài', '[1]'),
(3,  'T004S01', 2, 2, 'Thể loại nào trong các thể loại văn học sau đây ra đời trong giai đoạn kháng chiến chống Pháp (1946 - 1954)?', 'Truyện ngắn', 'kí', 'Tho', 'Truyện dai', '[1]'),
(4,  'T004S01', 2, 3, 'Quê hương của Quang Dũng ở:', '1915', '1921', '1922', '1925', '[3]'),
(5,  'T004S01', 3, 1, 'Quang Dũng sinh năm nào?', '1946', '1847', '1945', '1948', '[2]'),
(6,  'T006S01', 3, 1, 'Đoàn quân Tây tiến được thành lập năm nào sau đây:', 'Bi', 'Hùng(Hào Hùng)', 'Bi hùng', 'truyền cảm', '[4]'),
(7,  'T006S01', 4, 1, 'Cảm hứng chung của bài thơ Tây tiến là:', 'Hiện thực', 'Lãng Mạn', 'hiện thực XHCN', 'trào lộng', '[4]'),
(8,  'T006S01', 1, 3, 'Bút pháp tiêu biểu của bài thơ Tây Tiến là:', 'viết văn', 'Làm thơ', 'Soạn nhạc', 'viết phê bình', '[2]'),
(9,  'T006S01', 2, 2, 'Hoạt động nghệ thuật của Nguyễn Đình Thi nổi bật nhất ở lĩnh vực nào?', 'Bài thơ Hắc Hải', 'Dòng sông trong xanh', 'Tia nắng', 'người chiến sỹ', '[1]'),
(10, 'T006S01', 3, 1, 'Bài thơ Đất nước được in ở tập thơ nào?', 'Truyện kí', 'Thơ ca', 'Hò vè', 'tiểu thuyết', '[3]');

INSERT INTO Exams VALUES
('2024-01-01 19:30:00', 'T004S01', '2024-01-03 09:30:00', 45, 10.00, 'Bài kiểm tra giữa kì', 'không sử dụng tài liệu', true, '[1,2,3,4,5]'),
('2024-01-01 18:00:00', 'T004S01', '2024-01-05 13:00:00', 60, 10.00, 'Bài kiểm tra giữa kì', 'không sử dụng tài liệu', true, '[1,2,3,4,5]'),
('2024-01-02 22:30:00', 'T006S01', '2024-01-12 07:30:00', 60, 10.00, 'bài kiểm tra giữa kì', 'được sử dụng tài liệu', false, '[6,7,8,9,10]'),
('2024-03-02 21:30:00', 'T006S01', '2024-03-07 09:30:00', 45, 10.00, 'bài kiểm tra giữa kì', 'không sử dụng tài liệu', true, '[6,7,8,9,10]');

INSERT INTO Submissions VALUES
('2024-01-01 19:30:00', 'S001', 45, 9.00, '{"1": [2], "2": [1], "3": [1], "4": [3], "5": [1]}'),
('2024-01-01 18:00:00', 'S002', 43, 9.00, '{"1": [2], "2": [1], "3": [1], "4": [3], "5": [1]}'),
('2024-01-02 22:30:00', 'S003', 42, 8.00, '{"6": [4], "7": [4], "8": [2], "9": [1], "10": [3]}'),
('2024-03-02 21:30:00', 'S004', 39, 5.00, '{"6": [1], "7": [1], "8": [1], "9": [1], "10": [3]}'),
('2024-01-01 19:30:00', 'S005', 40, 7.00, '{"1": [3], "2": [1], "3": [1], "4": [3], "5": [1]}'),
('2024-01-01 18:00:00', 'S006', 41, 6.00, '{"1": [2], "2": [2], "3": [3], "4": [3], "5": [1]}'),
('2024-01-02 22:30:00', 'S007', 45, 6.00, '{"6": [4], "7": [1], "8": [1], "9": [1], "10": [3]}'),
('2024-03-02 21:30:00', 'S008', 44, 7.00, '{"6": [1], "7": [4], "8": [2], "9": [1], "10": [1]}');
