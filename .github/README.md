# Java Project
![](https://raw.githubusercontent.com/nguyenletientrien/QuizzServer/main/.github/img.png)

### Introduction
- Design Pattern: Three-layer
- Language (version): Java (v.17)
- GUI libraries - framework: javafx
- Database: MySQL

#### Database table relationship
![](https://raw.githubusercontent.com/nguyenletientrien/QuizzServer/main/.github/QuizzServerERD.png)

#### Project structure
```
src .
    ├── application         -> Contain Main function [Chua ham Main]
    ├── images              -> All images of project [Chua file anh cua du an]
    ├── model               -> Data Access Layer [Tang data]
    │   ├── DAO                 -> Data Access Oject (CRUD directly with database) [Them,sua,xoa truc tiep voi db]
    │   └── DTO                 -> Data Transfer Object (Objects to convert one row from table) [doi tuong de luu tung dong cua cac bang]
    ├── services            -> Business Logic Layer [Tang xu li nghiep vu]
    ├── utils               -> Utilities [Cac phuong thuc, thuoc tinh ho tro tu dinh nghia]
    └── view                -> Presentation Layer [Tang giao dien]
        ├── controller          -> UI controller [Dieu khien giao dien]
        ├── css                 -> css style sheet
        └── fxml                -> fxml for scenebuilder
```
