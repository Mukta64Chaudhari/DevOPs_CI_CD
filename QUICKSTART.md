# Library Management System - Quick Start Guide

## 🚀 Get Started in 5 Minutes

### Step 1: Build the Project
```bash
cd C:\Users\dheng\Library-management-system
mvn clean install
```
**Expected Output:** `BUILD SUCCESS`

### Step 2: Start the Application
```bash
mvn tomcat7:run
```
**Expected Output:** Application running at `http://localhost:8080/library`

### Step 3: Open in Browser
Visit: `http://localhost:8080/library`

### Step 4: Login
- **Username:** admin
- **Password:** admin123

### Step 5: Explore Features!

---

## 📋 What You Can Do

### ✅ Add Books
1. Click "Add Book" from dashboard
2. Enter Book ID, Title, Author
3. Click "Add Book"
4. See success message ✓

### ✅ View All Books
1. Click "View Books"
2. See all books in a table
3. Check availability status
4. Delete books if needed

### ✅ Search Books
1. Click "Search"
2. Choose: Search by Title or Author
3. Enter search term
4. Instant results!

### ✅ Issue a Book
1. Click "Issue/Return"
2. Select "Issue Book" tab
3. Enter Book ID
4. Click "Issue Book"
5. Book marked as "Issued" ✓

### ✅ Return a Book
1. Click "Issue/Return"
2. Select "Return Book" tab
3. Enter Book ID
4. Click "Return Book"
5. Book marked as "Available" ✓

---

## 🧪 Run Automated Tests (Optional)

**Prerequisites:** Application must be running in another terminal

```bash
# In a new terminal, run tests
mvn test
```

**Tests Include:**
- Adding books
- Issuing books
- Searching books
- Login validation
- Dashboard verification

---

## 📁 Project Structure Summary

```
Java Servlets (Backend):
├── LoginServlet.java        → Handles user login
├── BookServlet.java         → Handles all book operations
├── LogoutServlet.java       → Handles logout

Web Pages (Frontend):
├── index.html               → Login page
├── dashboard.html           → Main dashboard
├── add-book.html           → Add new books
├── view-books.html         → View all books
├── search-book.html        → Search functionality
├── issue-return-book.html  → Issue/Return books
├── style.css               → Beautiful styling
└── script.js               → JavaScript functions

Configuration:
├── pom.xml                 → Maven build config
└── web.xml                 → Web app config

Tests:
└── LibraryManagementTest.java  → Selenium tests
```

---

## 🔧 Troubleshooting

### Problem: Port 8080 is busy
**Solution:** Use a different port
```bash
mvn tomcat7:run -DskipTests -Dtomcat.port=8081
```
Then visit: `http://localhost:8081/library`

### Problem: Login not working
**Solution:** Use correct credentials
- Username: `admin`
- Password: `admin123`

### Problem: Tests fail
**Solution:** Make sure Tomcat is running
```bash
# Terminal 1: Start server
mvn tomcat7:run

# Terminal 2: Run tests
mvn test
```

### Problem: "Cannot build" or compile errors
**Solution:** Clean build
```bash
mvn clean install -DskipTests
mvn tomcat7:run
```

---

## 📊 Demo Data

The application comes with an in-memory database (ArrayList).

**Try This:**
1. Add 3-4 books
2. Issue one book
3. Search for books
4. View availability changes
5. Return the issued book

---

## 🎯 Key Features Implemented

✅ **Frontend:** ModernHTML5, CSS3, JavaScript
✅ **Backend:** Java Servlets, RESTful API
✅ **Authentication:** Login system with sessions
✅ **Database:** In-memory ArrayList (can upgrade to DB)
✅ **Search:** By title and author
✅ **Status Tracking:** Available/Issued books
✅ **Responsive Design:** Works on mobile and desktop
✅ **Testing:** 5 Selenium test cases
✅ **Error Handling:** Validation and error messages
✅ **Professional UI:** Modern gradient design

---

## 💡 Next Steps

### To Add More Features:

**1. Add "Category" field:**
- Add to Book.java
- Update BookServlet.java
- Update HTML forms

**2. Add Database (MySQL):**
- Add MySQL JDBC to pom.xml
- Replace ArrayList with SQL queries
- Add connection pooling

**3. Add More Users:**
- Create users table
- Use password hashing
- Implement role-based access

**4. Add Book Images:**
- Upload feature
- Store in server
- Display on pages

---

## 📚 Learning Resources

This project demonstrates:
- **Java Servlet development**
- **RESTful API design**
- **Web application architecture**
- **Selenium automated testing**
- **Responsive web design**
- **Maven project structure**
- **Session management**
- **Form validation**

---

## 🎓 Educational Value

Perfect for learning:
- Full-stack web development
- Backend-frontend communication
- Test-driven development
- DevOps concepts
- Software project structure

---

## 📞 Support

If you encounter any issues:
1. Check README.md for detailed documentation
2. Review error messages in browser console
3. Check terminal/console output
4. Verify all prerequisites are installed

---

## 🎉 Success Checklist

- [ ] Project built successfully (`mvn clean install`)
- [ ] Server running (`mvn tomcat7:run`)
- [ ] Can access login page
- [ ] Can login with admin/admin123
- [ ] Can add a book
- [ ] Can view books
- [ ] Can search books
- [ ] Can issue a book
- [ ] Can return a book
- [ ] Tests pass (`mvn test`)

Once all checks pass, you have a working **Library Management System**! 📚✨

---

## 📄 File Locations Reference

| File | Purpose | Location |
|------|---------|----------|
| Book.java | Book model | src/main/java/ |
| Library.java | Database service | src/main/java/ |
| LoginServlet.java | Authentication | src/main/java/ |
| BookServlet.java | API endpoints | src/main/java/ |
| index.html | Login page | src/main/webapp/ |
| dashboard.html | Main page | src/main/webapp/ |
| add-book.html | Add books | src/main/webapp/ |
| style.css | Styling | src/main/webapp/ |
| pom.xml | Dependencies | Root directory |
| web.xml | Configuration | src/main/webapp/WEB-INF/ |
| LibraryManagementTest.java | Tests | src/test/java/ |

---

**Happy Coding! 🚀**
