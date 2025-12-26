# 📚 Library Management System

> A comprehensive desktop application for managing library operations including books, members, and transactions - Undergraduate Software Engineering Project

[![Java](https://img.shields.io/badge/Java-100%25-orange.svg)](https://github.com/Yashraj2502/Library-management)
[![Contributors](https://img.shields.io/badge/Contributors-2-blue.svg)](https://github.com/Yashraj2502/Library-management/graphs/contributors)
[![Stars](https://img.shields.io/github/stars/Yashraj2502/Library-management)](https://github.com/Yashraj2502/Library-management/stargazers)

## 🎯 Project Overview

This Library Management System was developed as one of my first undergraduate projects. The application streamlines library operations by providing an intuitive interface for managing books, members, and lending transactions. The project received positive feedback from our course invigilator, particularly for its efficient **search functionality**.

## ✨ Key Features

### 📖 Book Management
- Add new books with detailed information (title, author, genre, ISBN)
- View comprehensive book catalog
- Search books by title, author, or genre
- Track book availability status
- Manage book genres and categories

### 👥 Member Management
- Register new library members
- View and update member information
- Track member borrowing history
- Member search functionality

### 🔄 Transaction Management
- **Issue books** to members with due date tracking
- **Return books** with automatic status updates
- View all active and historical transactions
- Overdue book tracking

### 🔍 Advanced Search ⭐
- **Fast and efficient search algorithm** (highlighted by course invigilator)
- Search across multiple fields simultaneously
- Real-time search results
- Filter by various criteria (genre, member, date)

### 📊 Dashboard & Statistics
- Overview of library operations
- Active loans and returns
- Member statistics
- Book availability metrics

## 🏗️ System Architecture

```
Library-Management/
├── Dashboard.java          # Main dashboard interface
├── DataBase.java          # Database connectivity and operations
├── login.java             # User authentication
├── bookListForm.java      # Book catalog display
├── MemberList.java        # Member management interface
├── issueBook.java         # Book issuing functionality
├── returnBook.java        # Book return processing
├── manageGenre.java       # Genre categorization
├── manageMember.java      # Member CRUD operations
└── functionClass.java     # Utility functions and helpers
```

## 🚀 Technologies Used

- **Java** - Core programming language
- **Java Swing** - GUI framework for desktop interface
- **JDBC** - Database connectivity
- **MySQL** - Database management
- **NetBeans** - IDE for development

## 🖥️ Screenshots

_[Add screenshots of the application here]_

### Recommended Screenshots:
1. Login screen
2. Main dashboard
3. Book catalog view
4. Search functionality in action
5. Issue/Return book interface

## 📝 How to Use

1. **Login** - Start the application and log in with credentials
2. **Add Books** - Navigate to "Manage Books" and add new entries
3. **Register Members** - Use "Manage Members" to register new library members
4. **Issue Books** - Select a book and member, then click "Issue Book"
5. **Return Books** - When a book is returned, process it through "Return Book"
6. **Search** - Use the search functionality to quickly find books or members
7. **View Statistics** - Check the dashboard for overview metrics

## 🎓 Academic Context

**Course:** Software Engineering / Database Management  
**Team Size:** 2 contributors  
**Duration:** 1 semester 
**Grade/Feedback:** Positive feedback from course invigilator, particularly for the search functionality implementation

## 🌟 What I Learned

This project was instrumental in my learning journey:
- Database design and SQL operations
- Java Swing GUI development
- CRUD operations implementation
- Search algorithm optimization
- Team collaboration and version control
- Software design patterns (MVC-like architecture)

## 🔮 Potential Enhancements

- [ ] Migrate to web-based interface (Spring Boot + React)
- [ ] Add email notifications for due dates
- [ ] Implement fine calculation for overdue books
- [ ] Generate reports (PDF/Excel)
- [ ] Add barcode scanning for books
- [ ] Multi-user roles (Admin, Librarian, Member)
- [ ] Book reservation system
- [ ] Integration with online book databases (ISBN lookup)

## 🐛 Known Issues

_[N/A for now]_

## 🤝 Contributors

- [@Yashraj2502](https://github.com/Yashraj2502)
- [@Tejasgavale07](https://github.com/Tejasgavale07)

---

**Note:** This was one of my first major software projects during undergraduate studies. While the code may not follow all current best practices, it represents an important milestone in my development journey and demonstrates foundational software engineering concepts.

⭐ If you find this project helpful for learning purposes, please consider giving it a star!

*Developed as part of undergraduate coursework - 2021*
