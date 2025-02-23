<h1 align="center">Quizz</h1>

<div align="center">A Quiz manager for schools, teachers or simply want to organize a quiz test</div>
<br/>

<a href="https://github.com/ttasc/Quizz"><img src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/Overview.png" width="100%" alt="" /></a>

## About
Quizz is a useful tool that supports the process of organizing and managing multiple-choice tests, especially in educational and training environments. This software helps automate many manual tasks, from creating test questions, managing question banks, to scoring and analyzing results.

Quizz is written in Java. It's free, open-source and cross-platform software.

## Features
- **Server**
  - Multiple workspaces for various educational purposes
  - Exam distribution via LAN
  - Management:
    - Exam (the exam question can be shuffled)
    - Submission
    - Group - Student
    - Subject - Question
- **Client**
  - Join the test and hope it's not too hard

## Installation
### Dependencies
- Java-17
- MySQL/MariaDB

> ##### Setup Database:
> - Username: `root`
> - Password: `123456789`
> - URL: `localhost:3306`
>
> To generate database, please run the sql script `QuizzServerInitTableMySQL.sql` via mysql-cli or mariadb-cli

### Download
See [releases](https://github.com/ttasc/Quizz/releases) for pre-built binaries.

### Build from source
Building from source requires Java-17, [Maven](https://maven.apache.org)

1. Clone this repo
2. Then open **QuizzServer** if you want to build *Server App* or open **QuizzClient** to build *Client App* and run this command:
    ```
    mvn package
    ```
3. The executable will be generated in **bin** folder named **QuizzServer.jar** or **QuizzClient.jar**

## Previews
🔍 Hover over image to view tooltip for more details.
<p align="center">
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/client1.png" alt="" title="Students have to fill their ID, IP address + port of the Teacher's server" />
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/client2.png" alt="" title="Students doing the exam" /> <br/> <br/>
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/server1.png" alt="" title="Teachers can monitor the status of students" />
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/server2.png" alt="" title="Teachers can view student submission details" /> <br/> <br/>
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/server3.png" alt="" title="Creating new Quiz Exam" />
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/server4.png" alt="" title="Exam Management main UI" /> <br/> <br/>
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/server5.png" alt="" title="Subject Management main UI" />
  <img align="center" width="49%" src="https://raw.githubusercontent.com/ttasc/Quizz/master/assets/server6.png" alt="" title="Student Management main UI" /> <br/> <br/>
</p>
