DROP DATABASE IF EXISTS QuizzServer;
CREATE DATABASE IF NOT EXISTS QuizzServer;

USE QuizzServer;

CREATE TABLE Person (
    PersonID VARCHAR(20) PRIMARY KEY NOT NULL,
    FirstName VARCHAR(40) NOT NULL,
    LastName VARCHAR(10) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Email VARCHAR(70),
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
    QuestionID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubjectID VARCHAR(30) NOT NULL,
    Chapter INT NOT NULL,
    Difficulty INT NOT NULL,
    Content VARCHAR(1000) NOT NULL,
    Answer1 VARCHAR(500) NOT NULL,
    Answer2 VARCHAR(500) NOT NULL,
    Answer3 VARCHAR(500) NOT NULL,
    Answer4 VARCHAR(500) NOT NULL,
    CorrectAnswers JSON NOT NULL,
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
('TCT001', 'Lê Thị'        , 'Hòa'   , '0911111110', 'hoa123@gmail.com' ),
('TCT006', 'Nguyễn Văn'    , 'Luận'  , '0922222220', 'luan1@gmail.com'  ),
('STS001', 'Trần Hoàng'    , 'Văn'   , '0933333330', 'van2@gmail.com'   ),
('TCT007', 'Lê Văn'        , 'Luân'  , '0944444440', 'luan2@gmail.com'  ),
('STS002', 'Nguyễn Lê'     , 'Lan'   , '0955555550', 'lan@gmail.com'    ),
('TCT002', 'Nguyễn Hoàng'  , 'Anh'   , '0966666660', 'anh@gmail.com'    ),
('STS004', 'Bùi Hoàng'     , 'Lam'   , '0977777770', 'lam@gmail.com'    ),
('STS005', 'Mai Ánh'       , 'Tuyết' , '0988888880', 'tuyet@gmail.com'  ),
('STS003', 'Hoàng Trần Văn', 'Đức'   , '0999999990', 'duc@gmail.com'    ),
('TCT009', 'Nguyễn Trung'  , 'Tín'   , '0391111110', 'tin@gmail.com'    ),
('TCT004', 'Nguyễn Văn'    , 'Thuận' , '0392222222', 'thuan@gmail.com'  ),
('STS010', 'Nguyễn Thị'    , 'Nhi'   , '0393333333', 'nhi@gmail.com'    ),
('STS008', 'Nguyễn Minh'   , 'Nhật'  , '0394444444', 'nhat@gmail.com'   ),
('STS007', 'Nguyễn Thu'    , 'Minh'  , '0395555555', 'minh@gmail.com'   ),
('TCT003', 'Trần Duy'      , 'Phương', '0395555555', 'phuong@gmail.com' ),
('TCT008', 'Mai Trúc'      , 'Lan'   , '0396666666', 'lan1@gmail.com'   ),
('TCT010', 'Mai Bích'      , 'Ngọc'  , '0397777777', 'ngoc@gmail.com'   ),
('STS009', 'Lê Như'        , 'Minh'  , '0398888888', 'minh1@gmail.com'  ),
('STS006', 'Lê Thị Bích'   , 'Tuyền' , '0399999999', 'tuyen@gmail.com'  ),
('TCT005', 'Hoàng Văn'     , 'Thụ'   , '0339999999', 'thu@gmail.com'    );

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
('T01010', 'S001');

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
('2024-05-08 09:00:00', 'T004S01', '2024-01-03 09:30:00', 45, 10.00, 'Bài kiểm tra giữa kì', 'không sử dụng tài liệu', true, '[1,2,3,4,5]'),
('2024-01-01 18:00:00', 'T004S01', '2024-01-05 13:00:00', 60, 10.00, 'Bài kiểm tra giữa kì', 'không sử dụng tài liệu', true, '[1,2,3,4,5]'),
('2024-01-02 22:30:00', 'T006S01', '2024-01-12 07:30:00', 60, 10.00, 'bài kiểm tra giữa kì', 'được sử dụng tài liệu', false, '[6,7,8,9,10]'),
('2024-03-02 21:30:00', 'T006S01', '2024-03-07 09:30:00', 45, 10.00, 'bài kiểm tra giữa kì', 'không sử dụng tài liệu', true, '[6,7,8,9,10]');

INSERT INTO Submissions VALUES
('2024-05-08 09:00:00', 'S001', 45, 9.00, '{"1": [2], "2": [1], "3": [1], "4": [3], "5": [1]}'),
('2024-01-01 18:00:00', 'S002', 43, 9.00, '{"1": [2], "2": [1], "3": [1], "4": [3], "5": [1]}'),
('2024-01-02 22:30:00', 'S003', 42, 8.00, '{"6": [4], "7": [4], "8": [2], "9": [1], "10": [3]}'),
('2024-03-02 21:30:00', 'S004', 39, 5.00, '{"6": [1], "7": [1], "8": [1], "9": [1], "10": [3]}'),
('2024-05-08 09:00:00', 'S005', 40, 7.00, '{"1": [3], "2": [1], "3": [1], "4": [3], "5": [1]}'),
('2024-01-01 18:00:00', 'S006', 41, 6.00, '{"1": [2], "2": [2], "3": [3], "4": [3], "5": [1]}'),
('2024-01-02 22:30:00', 'S007', 45, 6.00, '{"6": [4], "7": [1], "8": [1], "9": [1], "10": [3]}'),
('2024-03-02 21:30:00', 'S008', 44, 7.00, '{"6": [1], "7": [4], "8": [2], "9": [1], "10": [1]}');
