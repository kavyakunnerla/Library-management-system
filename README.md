
# 📚 Library Management System (Java + MySQL)

A simple and efficient console-based Library Management System developed using Java and MySQL.

## 🚀 Features
- Add new books
- Borrow books
- Return books
- View all books

## 🛠️ Technologies Used
- Java
- MySQL
- JDBC

## 💡 Description
This project demonstrates how to connect Java with a MySQL database using JDBC. It performs basic CRUD operations and simulates a real-world library system.

## ▶️ How to Run
1. Create a database in MySQL:
   CREATE DATABASE kavyadb;

2. Create table:
   CREATE TABLE books (
       book_id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(100),
       author VARCHAR(50),
       copies INT
   );

3. Update database credentials in code:
   DB_URL = jdbc:mysql://localhost:3306/kavyadb
   USER = root
   PASS = your_password

4. Run the Java file in Eclipse

## 🎯 Output
Menu-driven system:
1. Add Book
2. Borrow Book
3. Return Book
4. View Books
---

##  Screenshot 
[ library app screenshot]
https://github.com/kavyakunnerla/Library-management-system/blob/bfa57a800029d463238884f9b9afc122d1eb4330/Screenshot%20(285).png
-------
⭐ This project showcases Java, DBMS, and backend logic skills.
