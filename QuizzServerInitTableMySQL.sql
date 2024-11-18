DROP DATABASE IF EXISTS QuizzDB;

CREATE DATABASE IF NOT EXISTS QuizzDB;

USE QuizzDB;

drop table if EXISTS Workspaces;

CREATE TABLE Workspaces (
    WorkspaceID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Name VARCHAR(100) NOT NULL,
    Pin VARCHAR(6) NOT NULL,
    Archived BOOLEAN DEFAULT FALSE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

drop table if EXISTS Groups;

CREATE TABLE Groups (
    GroupID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    GroupName VARCHAR(100) NOT NULL,
    DateCreated DATETIME NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    WorkspaceID INT NOT NULL,
    CONSTRAINT FK_Groups_Workspaces FOREIGN KEY (WorkspaceID) REFERENCES Workspaces (WorkspaceID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE Students (
    UID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    StudentID VARCHAR(10) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Phone VARCHAR(12) NOT NULL,
    Email VARCHAR(255),
    GroupID INT NOT NULL,
    INDEX FIRSTNAME (FirstName ASC),
    INDEX LASTNAME (LastName ASC),
    CONSTRAINT FK_Students_Groups FOREIGN KEY (GroupID) REFERENCES Groups (GroupID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE Subjects (
    SubjectID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubjectName VARCHAR(100) NOT NULL,
    DateCreated DATETIME NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    WorkspaceID INT NOT NULL,
    CONSTRAINT FK_Subjects_Workspaces FOREIGN KEY (WorkspaceID) REFERENCES Workspaces (WorkspaceID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE Questions (
    QuestionID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubjectID INT NOT NULL,
    Chapter VARCHAR(255) NOT NULL,
    Difficulty INT NOT NULL,
    Content VARCHAR(1000) NOT NULL,
    Answers VARCHAR(1000) NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    CONSTRAINT FK_Questions_Subjects FOREIGN KEY (SubjectID) REFERENCES Subjects (SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE Exams (
    ExamID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubjectID INT NOT NULL,
    Name VARCHAR(255),
    Description VARCHAR(500),
    QuestionIDs VARCHAR(1000) NOT NULL,
    Archived BOOLEAN DEFAULT FALSE,
    CONSTRAINT FK_Exams_Subjects FOREIGN KEY (SubjectID) REFERENCES Subjects (SubjectID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE HostExams (
    HostExamID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    StartDateTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    TimeLimit INT NOT NULL,
    MaxScore DECIMAL(5, 2),
    isShuffled BOOLEAN DEFAULT FALSE,
    ExamQuestions JSON NOT NULL,
    ExamID INT NOT NULL,
    GroupID INT NOT NULL,
    CONSTRAINT FK_HostExams_Exams FOREIGN KEY (ExamID) REFERENCES Exams (ExamID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_HostExams_Groups FOREIGN KEY (GroupID) REFERENCES Groups (GroupID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE Submissions (
    HostExamID INT NOT NULL,
    UID INT NOT NULL,
    TimeTaken INT NOT NULL,
    Score DECIMAL(5, 2) NOT NULL,
    AnswerSelecteds JSON NOT NULL,
    PRIMARY KEY (HostExamID, UID),
    CONSTRAINT FK_Submissions_HostExams FOREIGN KEY (HostExamID) REFERENCES HostExams (HostExamID) ON DELETE CASCADE,
    CONSTRAINT FK_Submissions_Students FOREIGN KEY (UID) REFERENCES Students (UID) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

INSERT INTO
    Workspaces
VALUES (1, 'DHSG', '111111', false),
    (2, 'DHKHTN', '111111', false),
    (3, 'DHBK', '111111', false);

INSERT INTO
    Groups
VALUES (
        1,
        'Nhom 1 - DSTT',
        '2024-05-08 09:00:00',
        false,
        1
    ),
    (
        2,
        'Nhom 9 Python',
        '2024-01-01 18:00:00',
        false,
        2
    ),
    (
        3,
        'Nhom 3 - Giai Tich',
        '2024-01-02 22:30:00',
        false,
        3
    ),
    (
        4,
        'Nhom 7 - XSTK',
        '2024-03-02 21:30:00',
        false,
        1
    ),
    (
        5,
        'Nhom 5 Co so lap trin',
        '2023-09-30 15:03:08',
        false,
        3
    );

INSERT INTO
    Students
VALUES (
        '1',
        'ST001',
        'Trần Hoàng',
        'Văn',
        '0933333330',
        'van2@gmail.com',
        1
    ),
    (
        '2',
        'ST002',
        'Nguyễn Lê',
        'Lan',
        '0955555550',
        'lan@gmail.com',
        2
    ),
    (
        '3',
        'ST003',
        'Bùi Hoàng',
        'Lam',
        '0977777770',
        'lam@gmail.com',
        3
    ),
    (
        '4',
        'ST004',
        'Mai Ánh',
        'Tuyết',
        '0988888880',
        'tuyet@gmail.com',
        4
    ),
    (
        '5',
        'ST005',
        'Hoàng Trần Văn',
        'Đức',
        '0999999990',
        'duc@gmail.com',
        5
    ),
    (
        '6',
        'ST006',
        'Nguyễn Thị',
        'Nhi',
        '0393333333',
        'nhi@gmail.com',
        3
    ),
    (
        '7',
        'ST007',
        'Nguyễn Minh',
        'Nhật',
        '0394444444',
        'nhat@gmail.com',
        2
    ),
    (
        '8',
        'ST008',
        'Nguyễn Thu',
        'Minh',
        '0395555555',
        'minh@gmail.com',
        1
    ),
    (
        '9',
        'ST009',
        'Lê Như',
        'Minh',
        '0398888888',
        'minh1@gmail.com',
        4
    ),
    (
        '10',
        'ST010',
        'Lê Thị Bích',
        'Tuyền',
        '0399999999',
        'tuyen@gmail.com',
        5
    );

INSERT INTO
    Subjects
VALUES (
        1,
        'Văn',
        '2024-05-08 09:00:00',
        false,
        1
    ),
    (
        2,
        'Sử',
        '2024-01-01 18:00:00',
        false,
        2
    ),
    (
        3,
        'Địa Lí',
        '2024-01-02 22:30:00',
        false,
        3
    );

INSERT INTO
    Questions
VALUES (
        1,
        1,
        'Chapter 1',
        1,
        'Sáng tác của Nguyễn Ái Quốc, Hồ Chí Minh không bao gồm những thể loại nào trong các thể loại sau đây:',
        '[{"answerID":1,"questionID":1,"content":"Văn chính luận","isCorrect":true},
    {"answerID":2,"questionID":1,"content":"Miêu tả","isCorrect":false},
    {"answerID":3,"questionID":1,"content":"Truyện","isCorrect":false},
    {"answerID":4,"questionID":1,"content":"Thơ","isCorrect":false}]',
        false
    ),
    (
        2,
        1,
        'Chapter 1',
        4,
        'Tuyên ngôn độc lập của Hồ Chí Minh được viết theo thể loại nào sau đây:',
        '[{"answerID":1,"questionID":2,"content":"Văn chính luận","isCorrect":true},
    {"answerID":2,"questionID":2,"content":"Kí","isCorrect":false},
    {"answerID":3,"questionID":2,"content":"Truyện dài","isCorrect":false},
    {"answerID":4,"questionID":2,"content":"Thơ","isCorrect":false}]',
        false
    ),
    (
        3,
        1,
        'Chapter 2',
        5,
        'Thể loại nào trong các thể loại văn học sau đây ra đời trong giai đoạn kháng chiến chống Pháp (1946 - 1954)?',
        '[{"answerID":1,"questionID":3,"content":"Truyện ngắn","isCorrect":true},
    {"answerID":2,"questionID":3,"content":"Kí","isCorrect":false},
    {"answerID":3,"questionID":3,"content":"Truyện dài","isCorrect":true},
    {"answerID":4,"questionID":3,"content":"Thơ","isCorrect":false}]',
        false
    ),
    (
        4,
        1,
        'Chapter 2',
        3,
        'Quê hương của Quang Dũng ở:',
        '[{"answerID":1,"questionID":4,"content":"1915","isCorrect":true},
    {"answerID":2,"questionID":4,"content":"1921","isCorrect":false},
    {"answerID":3,"questionID":4,"content":"1922","isCorrect":true},
    {"answerID":4,"questionID":4,"content":"1925","isCorrect":false}]',
        false
    ),
    (
        5,
        1,
        'Chapter 4',
        2,
        'Quang Dũng sinh năm nào?',
        '[{"answerID":1,"questionID":5,"content":"1946","isCorrect":false},
    {"answerID":2,"questionID":5,"content":"1847","isCorrect":false},
    {"answerID":3,"questionID":5,"content":"1945","isCorrect":true},
    {"answerID":4,"questionID":5,"content":"1948","isCorrect":false}]',
        false
    ),
    (
        6,
        1,
        'Chapter 3',
        1,
        'Đoàn quân Tây tiến được thành lập năm nào sau đây:',
        '[{"answerID":1,"questionID":6,"content":"Bi","isCorrect":false},
    {"answerID":2,"questionID":6,"content":"Hùng(Hào Hùng)","isCorrect":false},
    {"answerID":3,"questionID":6,"content":"Bi hùng","isCorrect":false},
    {"answerID":4,"questionID":6,"content":"Truyền cảm","isCorrect":true}]',
        false
    ),
    (
        7,
        1,
        'Chapter 4',
        5,
        'Cảm hứng chung của bài thơ Tây tiến là:',
        '[{"answerID":1,"questionID":7,"content":"Hiện thực","isCorrect":false},
    {"answerID":2,"questionID":7,"content":"Lãng Mạn","isCorrect":false},
    {"answerID":3,"questionID":7,"content":"Hiện thực XHCN","isCorrect":false},
    {"answerID":4,"questionID":7,"content":"Trào lộng","isCorrect":true}]',
        false
    ),
    (
        8,
        1,
        'Chapter 3',
        2,
        'Bút pháp tiêu biểu của bài thơ Tây Tiến là:',
        '[{"answerID":1,"questionID":8,"content":"Viết văn","isCorrect":false},
    {"answerID":2,"questionID":8,"content":"Làm thơ","isCorrect":false},
    {"answerID":3,"questionID":8,"content":"Soạn nhạc","isCorrect":false},
    {"answerID":4,"questionID":8,"content":"Viết phê bình","isCorrect":true}]',
        false
    ),
    (
        9,
        1,
        'Chapter 3',
        2,
        'Hoạt động nghệ thuật của Nguyễn Đình Thi nổi bật nhất ở lĩnh vực nào?',
        '[{"answerID":1,"questionID":9,"content":"Bài thơ Hắc Hải","isCorrect":false},
    {"answerID":2,"questionID":9,"content":"Dòng sông trong xanh","isCorrect":false},
    {"answerID":3,"questionID":9,"content":"Tia nắng","isCorrect":false},
    {"answerID":4,"questionID":9,"content":"Người chiến sỹ","isCorrect":true}]',
        false
    ),
    (
        10,
        1,
        'Chapter 2',
        4,
        'Bài thơ Đất nước được in ở tập thơ nào?',
        '[{"answerID":1,"questionID":10,"content":"Truyện kí","isCorrect":false},
    {"answerID":2,"questionID":10,"content":"Thơ ca","isCorrect":false},
    {"answerID":3,"questionID":10,"content":"Hò vè","isCorrect":false},
    {"answerID":4,"questionID":10,"content":"Tiểu thuyết","isCorrect":true}]',
        false
    );