DROP DATABASE IF EXISTS QuizzServer;
CREATE DATABASE IF NOT EXISTS QuizzServer;

USE QuizzServer;

CREATE TABLE Workspaces (
    WorkspaceID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Name VARCHAR(30) NOT NULL,
    Pin VARCHAR(8) NOT NULL,
    Archived BOOLEAN DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Students (
    StudentID VARCHAR(20) PRIMARY KEY NOT NULL,
    StudentCODE VARCHAR(30) NOT NULL,
    FirstName VARCHAR(40) NOT NULL,
    LastName VARCHAR(10) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Email VARCHAR(70),
    INDEX FIRSTNAME (FirstName ASC),
    INDEX LASTNAME (LastName ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE SGroups (
    SGroupID VARCHAR(30) PRIMARY KEY NOT NULL,
    SGroupName VARCHAR(50) NOT NULL,
    DateCreated DATETIME NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    WorkspaceID BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE SGroupStudents (
    SGroupID VARCHAR(30) NOT NULL,
    StudentID VARCHAR(30) NOT NULL,
    PRIMARY KEY(SGroupID, StudentID),
    CONSTRAINT FK_SGroupStudents_SGroupID FOREIGN KEY (SGroupID) REFERENCES SGroups(SGroupID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_SGroupStudents_StudentID FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Subjects (
    SubjectID VARCHAR(30) PRIMARY KEY NOT NULL,
    SubjectName VARCHAR(100) NOT NULL,
    DateCreated DATETIME NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    WorkspaceID BIGINT NOT NULL,
    CONSTRAINT FK_Subject_Workspace FOREIGN KEY (WorkspaceID) REFERENCES Workspaces(WorkspaceID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Questions (
    QuestionID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubjectID VARCHAR(30) NOT NULL,
    Chapter INT NOT NULL,
    Difficulty INT NOT NULL,
    Content VARCHAR(1000) NOT NULL,
    Answers JSON NOT NULL,
    CorrectAnswers JSON NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    INDEX CHAPTER (Chapter ASC),
    INDEX DIFFICULTY (Difficulty ASC),
    CONSTRAINT FK_Questions_SubjectID FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Exams (
    ExamID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubjectID VARCHAR(30) NOT NULL,
    Name VARCHAR(100),
    Description VARCHAR(500),
    QuestionIDs JSON NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    CONSTRAINT FK_Exams_SubjectID FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE HostExams (
    HostExamID DATETIME DEFAULT CURRENT_TIMESTAMP PRIMARY KEY,
    StartDateTime DATETIME NOT NULL,
    TimeLimit INT NOT NULL,
    MaxScore DECIMAL(5, 2),
    isShuffled BOOLEAN DEFAULT FALSE,
    ExamQuestions JSON NOT NULL,
    ExamID BIGINT NOT NULL,
    SGroupID VARCHAR(30) NOT NULL,
    CONSTRAINT FK_HostExams_ExamID FOREIGN KEY (ExamID) REFERENCES Exams(ExamID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_HostExams_SGroupID FOREIGN KEY (SGroupID) REFERENCES SGroups(SGroupID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Submissions (
    HostExamID DATETIME NOT NULL,
    StudentID VARCHAR(15) NOT NULL,
    TimeTaken INT NOT NULL,
    Score DECIMAL(5, 2) NOT NULL,
    AnswerSelecteds JSON NOT NULL,
    PRIMARY KEY(HostExamID, StudentID),
    CONSTRAINT FK_Submissions_HostExamID FOREIGN KEY (HostExamID) REFERENCES HostExams(HostExamID) ON DELETE CASCADE,
    CONSTRAINT FK_Submissions_StudentID FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO Workspaces VALUES
(1, 'DHSG'  , '111111', false),
(2, 'DHKHTN', '111111', false),
(3, 'DHBK'  , '111111', false);

INSERT INTO Students VALUES
('1', 'ST001', 'Trần Hoàng'    , 'Văn'   , '0933333330', 'van2@gmail.com'   ),
('2', 'ST002', 'Nguyễn Lê'     , 'Lan'   , '0955555550', 'lan@gmail.com'    ),
('3', 'ST003', 'Bùi Hoàng'     , 'Lam'   , '0977777770', 'lam@gmail.com'    ),
('4', 'ST004', 'Mai Ánh'       , 'Tuyết' , '0988888880', 'tuyet@gmail.com'  ),
('5', 'ST005', 'Hoàng Trần Văn', 'Đức'   , '0999999990', 'duc@gmail.com'    ),
('6', 'ST006', 'Nguyễn Thị'    , 'Nhi'   , '0393333333', 'nhi@gmail.com'    ),
('7', 'ST007', 'Nguyễn Minh'   , 'Nhật'  , '0394444444', 'nhat@gmail.com'   ),
('8', 'ST008', 'Nguyễn Thu'    , 'Minh'  , '0395555555', 'minh@gmail.com'   ),
('9', 'ST009', 'Lê Như'        , 'Minh'  , '0398888888', 'minh1@gmail.com'  ),
('10','ST010', 'Lê Thị Bích'   , 'Tuyền' , '0399999999', 'tuyen@gmail.com'  );

INSERT INTO SGroups VALUES
('1', 'Nhom 1 - DSTT'        , '2024-05-08 09:00:00', false, '1'),
('2', 'Nhom 9 Python'        , '2024-01-01 18:00:00', false, '2'),
('3', 'Nhom 3 - Giai Tich'   , '2024-01-02 22:30:00', false, '3'),
('4', 'Nhom 7 - XSTK'        , '2024-03-02 21:30:00', false, '1'),
('5', 'Nhom 5 Co so lap trin', '2023-09-30 15:03:08', false, '3');

INSERT INTO SGroupStudents VALUES
('1', '1' ),
('1', '2' ),
('3', '3' ),
('5', '4' ),
('5', '5' ),
('4', '6' ),
('4', '7' ),
('4', '8' ),
('2', '9' ),
('1', '10');

INSERT INTO Subjects VALUES
('1', 'Văn'    , '2024-05-08 09:00:00', false, '1'),
('2', 'Sử'     , '2024-01-01 18:00:00', false, '2'),
('3', 'Địa Lí' , '2024-01-02 22:30:00', false, '3');

INSERT INTO Questions VALUES
(1,  '1', 1, 1, 'Sáng tác của Nguyễn Ái Quốc, Hồ Chí Minh không bao gồm những thể loại nào trong các thể loại sau đây:',
    '{"A": "Văn chính luận", "B": "Miêu tả", "C": "Truyện", "D": "thơ"}', '[2]', false),
(2,  '1', 1, 2, 'Tuyên ngôn độc lập của Hồ Chí Minh được viết theo thể loại nào sau đây:',
    '{"A": "Văn chính luận", "B": "kí", "C": "thơ", "D": "Truyện dài"}', '[1]', false),
(3,  '1', 2, 2, 'Thể loại nào trong các thể loại văn học sau đây ra đời trong giai đoạn kháng chiến chống Pháp (1946 - 1954)?',
    '{"A": "Truyện ngắn", "B": "kí", "C": "Tho", "D": "Truyện dai"}', '[1]', false),
(4,  '3', 2, 3, 'Quê hương của Quang Dũng ở:',
    '{"A": "1915", "B": "1921", "C": "1922", "D": "1925"}', '[3]', false),
(5,  '1', 3, 4, 'Quang Dũng sinh năm nào?',
    '{"A": "1946", "B": "1847", "C": "1945", "D": "1948"}', '[2]', false),
(6,  '2', 3, 1, 'Đoàn quân Tây tiến được thành lập năm nào sau đây:',
    '{"A": "Bi", "B": "Hùng(Hào Hùng)", "C": "Bi hùng", "D": "truyền cảm"}', '[4]', false),
(7,  '1', 4, 5, 'Cảm hứng chung của bài thơ Tây tiến là:',
    '{"A": "Hiện thực", "B": "Lãng Mạn", "C": "hiện thực XHCN", "D": "trào lộng"}', '[4]', false),
(8,  '1', 1, 3, 'Bút pháp tiêu biểu của bài thơ Tây Tiến là:',
    '{"A": "viết văn", "B": "Làm thơ", "C": "Soạn nhạc", "D": "viết phê bình"}', '[2]', false),
(9,  '1', 2, 5, 'Hoạt động nghệ thuật của Nguyễn Đình Thi nổi bật nhất ở lĩnh vực nào?',
    '{"A": "Bài thơ Hắc Hải", "B": "Dòng sông trong xanh", "C": "Tia nắng", "D": "người chiến sỹ"}', '[1]', false),
(10, '1', 3, 1, 'Bài thơ Đất nước được in ở tập thơ nào?',
    '{"A": "Truyện kí", "B": "Thơ ca", "C": "Hò vè", "D": "tiểu thuyết"}', '[3]', false);

