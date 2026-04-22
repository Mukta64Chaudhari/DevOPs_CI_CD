# Library Management System - Complete Web Application

![CI/CD Pipeline](https://github.com/Mukta64Chaudhari/DevOPs_CI_CD/actions/workflows/ci.yml/badge.svg)

**Pipeline:** Passing ✅ | **Deployment:** Live on Render ✅ | **Quality Gate:** SonarCloud ✅

A modern, interactive web-based Library Management System built with Java Servlets, HTML5, CSS3, and JavaScript with Selenium testing.

## Project Overview

This is a full-stack web application that allows users to:
- ✅ Add and manage books in a library
- ✅ View all books with availability status
- ✅ Search books by title or author
- ✅ Issue and return books
- ✅ Track book availability in real-time
- ✅ User authentication with login system
- ✅ Responsive and modern UI
- ✅ Comprehensive Selenium tests

## Technology Stack

### Backend
- **Java 17**
- **Apache Tomcat 9** (via Maven plugin or Docker)
- **Servlets** (javax.servlet API 4.0)
- **GSON** (for JSON processing)

### Frontend
- **HTML5**
- **CSS3** (Responsive Design)
- **JavaScript (Vanilla)**

### Testing
- **Selenium WebDriver 4.15**
- **JUnit 4**
- **WebDriverManager** (for driver management)

### Build Tool
- **Apache Maven 3.6+**

## Project Structure

```
library-management-system/
├── pom.xml                              # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Book.java               # Book model class
│   │   │   ├── Library.java            # Library service (Singleton)
│   │   │   ├── LoginServlet.java       # Authentication servlet
│   │   │   ├── BookServlet.java        # Main REST API servlet
│   │   │   └── LogoutServlet.java      # Logout servlet
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml             # Web deployment descriptor
│   │       ├── index.html              # Login page
│   │       ├── dashboard.html          # Main dashboard
│   │       ├── add-book.html           # Add book form
│   │       ├── view-books.html         # View all books
│   │       ├── search-book.html        # Search books
│   │       ├── issue-return-book.html  # Issue/Return forms
│   │       ├── style.css               # Styling
│   │       └── script.js               # Common JavaScript utilities
│   └── test/
│       └── java/
│           └── LibraryManagementTest.java  # Selenium test cases
└── target/                              # Build output
```

## Prerequisites

1. **Java 17 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6.0 or higher**
   ```bash
   mvn -version
   ```

3. **Tomcat 9+ or embedded Tomcat via Maven**

4. **Chrome/Chromium Browser** (for Selenium tests)

5. **Chrome WebDriver** (automatically downloaded by WebDriverManager)

## Setup Instructions

### 1. Clone or Extract the Project

```bash
cd C:\Users\dheng\Library-management-system
```

### 2. Build the Project

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile Java classes
- Package as WAR file
- Run any available tests

### 3. Run the Application

**Option A: Using Maven Tomcat Plugin**

```bash
mvn tomcat7:run
```

The application will be available at: `http://localhost:8080/library`

**Option B: Deploy to External Tomcat**

1. Build the WAR file:
   ```bash
   mvn clean package
   ```

2. Copy the WAR from `target/library.war` to Tomcat's `webapps` folder

3. Start Tomcat and access at `http://localhost:8080/library`

## Login Credentials

```
Username: admin
Password: admin123
```

## File Descriptions

### Backend Files

#### `Book.java`
- Model class for book entities
- Properties: id, title, author, isIssued
- Methods:
  - `issueBook()` - Mark book as issued
  - `returnBook()` - Mark book as returned
  - Getters and setters for all properties

**File Location:** `src/main/java/Book.java`

#### `Library.java`
- Service class using Singleton pattern
- Manages all book operations
- Key methods:
  - `getInstance()` - Get singleton instance
  - `addBook(Book)` - Add a new book
  - `getAllBooks()` - Get all books
  - `searchByTitle(String)` - Search books by title
  - `searchByAuthor(String)` - Search books by author
  - `issueBook(int)` - Issue a book by ID
  - `returnBook(int)` - Return a book by ID
  - `deleteBook(int)` - Delete a book by ID

**File Location:** `src/main/java/Library.java`

#### `LoginServlet.java`
- Handles user authentication
- Validates credentials (hardcoded)
- Creates session for authenticated users
- Redirects to dashboard on success

**File Location:** `src/main/java/LoginServlet.java`

**Endpoint:** `POST /login`

#### `BookServlet.java`
- Main REST API servlet for all book operations
- Requires user session/authentication
- Supports multiple actions: add, issue, return, delete, search, get all

**File Location:** `src/main/java/BookServlet.java`

**Endpoints:**
- `GET /api/books?action=all` - Get all books
- `GET /api/books?action=search&query=TEXT&type=title|author` - Search books
- `GET /api/books?action=get&id=BOOK_ID` - Get specific book
- `POST /api/books` - Add/Issue/Return/Delete books

#### `LogoutServlet.java`
- Invalidates user session
- Redirects to login page

**File Location:** `src/main/java/LogoutServlet.java`

**Endpoint:** `GET /logout`

### Frontend Files

#### `index.html` - Login Page
- Simple login form
- Username and password fields
- Form validation
- Demo credentials display

**Features:**
- Clean login interface
- Client-side validation
- Credentials hint for demo

#### `dashboard.html` - Main Dashboard
- Welcome message
- Statistics cards (Total, Available, Issued books)
- Quick action buttons
- Navigation menu
- Real-time statistics loading

**Features:**
- Dynamic book statistics
- Quick navigation
- Professional layout

#### `add-book.html` - Add Book Form
- Form to add new books
- Fields: Book ID, Title, Author
- Form validation
- Success/Error messages

**Features:**
- Real-time validation
- Auto-redirect after success
- Clear error messages

#### `view-books.html` - View All Books
- Displays all books in a table
- Shows ID, Title, Author, Status (Available/Issued)
- Delete book functionality
- Responsive table design

**Features:**
- Sortable table
- Delete functionality
- Status badges
- Loading spinner

#### `search-book.html` - Search Books
- Search by title or author
- Real-time search results
- Filter options
- Results displayed in table format

**Features:**
- Dual search modes
- Live search results
- Auto-debounce for performance
- Clear search option

#### `issue-return-book.html` - Issue/Return Books
- Tabbed interface for Issue and Return
- Input book ID
- Form validation
- Success/Error messages
- Book availability check

**Features:**
- Clean tabbed design
- Status messages
- Form validation
- Real-time feedback

#### `style.css` - Styling
- Modern gradient design (purple theme)
- Responsive grid layouts
- Mobile-first design
- Smooth animations and transitions
- Professional color scheme
- Form styling
- Table styling
- Button styles
- Navigation bar styling

**Features:**
- Responsive design (mobile, tablet, desktop)
- Smooth transitions
- Professional gradients
- Accessibility considerations

#### `script.js` - JavaScript Utilities
- Common utility functions:
  - `checkUserSession()` - Verify user is logged in
  - `apiCall()` - Wrapper for API requests
  - `debounce()` - Debounce function
  - `throttle()` - Throttle function
  - `StorageManager` - Local storage helper

**Features:**
- Session management
- API error handling
- Local storage utilities
- Common helper functions

### Configuration Files

#### `pom.xml` - Maven Configuration
- Project metadata
- Dependencies (Servlet, Selenium, JUnit, GSON, WebDriverManager)
- Build plugins (Compiler, WAR, Tomcat)
- Java 17 compatibility

#### `web.xml` - Web Deployment Descriptor
- Welcome file configuration
- Session settings
- Spring container servlet configuration

### Test Files

#### `LibraryManagementTest.java`
- Comprehensive Selenium test suite
- 5 test cases covering critical functionality

**Test Cases:**
1. **testAddBookViaUI()** - Tests adding a book through UI
   - Login verifies
   - Navigate to Add Book page
   - Fill and submit form
   - Verify success message

2. **testIssueBook()** - Tests issuing a book
   - Add a test book
   - Navigate to Issue/Return page
   - Issue the book
   - Verify status change in View Books page

3. **testSearchBookByTitle()** - Tests search functionality
   - Login
   - Navigate to Search page
   - Search by title
   - Verify results

4. **testLoginPage()** - Validates login page elements
   - Verify form existence
   - Check required fields

5. **testDashboardPageAfterLogin()** - Validates dashboard after login
   - Verify successful redirect
   - Check page title

**File Location:** `src/test/java/LibraryManagementTest.java`

## Usage Guide

### Adding a Book

1. Click "Add Book" button from dashboard
2. Fill in the following details:
   - **Book ID**: Unique identifier (number)
   - **Title**: Book title
   - **Author**: Author name
3. Click "Add Book" button
4. View success message
5. Redirect to View Books page

### Viewing Books

1. Click "View Books" from dashboard
2. See all books in table format with:
   - Book ID
   - Title
   - Author
   - Current Status (Available/Issued)
   - Delete option

### Searching Books

1. Click "Search" from dashboard
2. Choose search type (Title or Author)
3. Enter search query
4. Instant results display
5. Click "Clear" to reset search

### Issuing a Book

1. Click "Issue/Return" from dashboard
2. Select "Issue Book" tab
3. Enter Book ID of the book to issue
4. Click "Issue Book"
5. View success message
6. Book status changes from "Available" to "Issued"

### Returning a Book

1. Click "Issue/Return" from dashboard
2. Select "Return Book" tab
3. Enter Book ID of the issued book
4. Click "Return Book"
5. View success message
6. Book status changes from "Issued" to "Available"

## Running Tests

### Prerequisites for Testing
1. Application must be running: `mvn tomcat7:run`
2. Chrome browser installed
3. WebDriver manager will auto-download ChromeDriver

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=LibraryManagementTest
```

### Run Specific Test Method

```bash
mvn test -Dtest=LibraryManagementTest#testAddBookViaUI
```

### Test Execution Flow

1. Maven starts Tomcat automatically (via failsafe plugin if configured)
2. Tests open Chrome browser
3. Tests execute scenarios
4. Browser closes
5. Test results printed

### Test Output Example

```
========== Test 1: Add Book via UI ==========
✓ Opened login page
✓ Login successful
✓ Redirected to dashboard
✓ Navigated to Add Book page
✓ Filled book details - ID: 12345, Title: Test Book 12345, Author: Test Author
✓ Form submitted
✓ Success message displayed: Book added successfully
========== Test 1 PASSED ==========
```

## API Endpoints

### Authentication
- **POST /login** - User login
  - Parameters: username, password
  - Response: Redirect to dashboard or error page

### Book Operations (All require session)
Base URL: `http://localhost:8080/library/api/books`

#### GET Endpoints
```
GET /api/books?action=all
Response: JSON array of all books

GET /api/books?action=search&query=QUERY&type=title|author
Response: JSON array of matching books

GET /api/books?action=get&id=BOOK_ID
Response: JSON object of specific book
```

#### POST Endpoints
```
POST /api/books
- action=add&id=ID&title=TITLE&author=AUTHOR
- action=issue&id=BOOK_ID
- action=return&id=BOOK_ID
- action=delete&id=BOOK_ID
Response: JSON with success status and message
```

## Session Management

- Sessions are cookie-based
- Session timeout: Default (depends on Tomcat configuration)
- Session validation on every API call
- Unauthorized requests redirect to login

## Error Handling

- Invalid login: Error page with retry option
- Missing fields: Form validation alerts
- Book not found: Error message displayed
- Book already issued: Error message
- Network errors: Browser console logs

## Performance Considerations

1. **Search**: Implemented with debounce (500ms)
2. **Session validation**: Checked on page load
3. **Data storage**: ArrayList in memory (non-persistent)
4. **Rendering**: Minimal DOM manipulation
5. **CSS**: Minimal file size with optimization

## Data Storage

- **Current Implementation**: In-memory ArrayList
- **Data Loss**: All data lost on server restart
- **Enhancement**: Can be upgraded to database

### To Add Database Support

```java
// Replace ArrayList with database queries
// Recommended: MySQL/PostgreSQL with JDBC or JPA
// Dependencies to add:
// - JDBC driver (MySQL/PostgreSQL)
// - Connection pooling library (HikariCP)
// - OR use Spring Data JPA
```

## Browser Compatibility

- ✅ Chrome/Chromium 90+
- ✅ Firefox 88+
- ✅ Safari 14+
- ✅ Edge 90+
- ✅ Mobile browsers (iOS Safari, Chrome Mobile)

## Troubleshooting

### Issue: Port 8080 already in use
```bash
# Use different port
mvn tomcat7:run -DskipTests -Dtomcat.port=8081
```

### Issue: WebDriver not found
```bash
# WebDriverManager should auto-download
# If not, manually download from:
# https://chromedriver.chromium.org/
```

### Issue: Tests fail with "localhost refused"
```bash
# Ensure Tomcat is running in another terminal
mvn tomcat7:run
# Then run tests in another terminal
mvn test
```

### Issue: Login fails
```
- Check username/password (admin/admin123)
- Check browser console for errors
- Verify servlet is deployed
```

### Issue: Books not showing
```
- Ensure books were successfully added
- Check browser console for API errors
- Verify session is active
```

## Development Notes

### Adding New Features

1. **New Search Type**: Update BookServlet.java and search-book.html
2. **New Book Property**: Update Book.java, BookServlet, and HTML forms
3. **New Page**: Create HTML file with navigation link
4. **New API Endpoint**: Add to BookServlet.java with action parameter

### Code Organization

- **Controllers**: Servlet classes
- **Models**: Book.java, Library.java  
- **Views**: HTML files
- **Styles**: style.css
- **Logic**: script.js, BookServlet

### Best Practices Implemented

✅ Singleton pattern for Library
✅ MVC architecture
✅ Responsive design
✅ Input validation
✅ Error handling
✅ Session management
✅ RESTful API design
✅ Unit testing with Selenium
✅ Clean code principles
✅ Security considerations (session validation)

## Future Enhancements

1. **Database Integration**
   - Replace ArrayList with MySQL/PostgreSQL
   - Implement CRUD operations with SQL

2. **User Management**
   - Multiple user roles (Admin, Librarian, Member)
   - User accounts and profiles
   - Password hashing

3. **Advanced Features**
   - Book reservations
   - Due date tracking
   - Late fee calculation
   - Book categories/genres
   - Member dashboard

4. **Security**
   - JWT authentication
   - HTTPS support
   - SQL injection prevention
   - CSRF protection

5. **UI/UX Improvements**
   - Dark mode
   - Advanced filtering
   - Export to PDF
   - Book cover images
   - Barcode scanning

## Deployment

### Local Deployment (Development)
```bash
mvn tomcat7:run
```

### Production Deployment with Tomcat
```bash
# Build WAR
mvn clean package

# Copy to Tomcat
cp target/library.war $TOMCAT_HOME/webapps/

# Start Tomcat
$TOMCAT_HOME/bin/startup.sh
```

### Cloud Deployment with Render

1. **Create Render Account**: Sign up at render.com
2. **Create Web Service**:
   - Connect GitHub repository
   - Select Docker environment
   - Set build command: `docker build -t library-management .`
   - Set start command: `docker run -p 8080:8080 library-management`
3. **Configure Environment**:
   - Port: 8080
   - Health check path: /
4. **Set up Deploy Hook**: Copy the webhook URL for CI/CD automation

### Deployment URLs

- **Local**: `http://localhost:8080`
- **Render**: `https://your-app-name.onrender.com`

## Docker Deployment

### Local Docker Build and Run

1. **Build the Docker image:**
   ```bash
   docker build -t library-management .
   ```

2. **Run the container:**
   ```bash
   docker run -p 8080:8080 library-management
   ```

3. **Access the application:**
   - Open browser: `http://localhost:8080`
   - Login with: admin / admin123

### Using Docker Compose

```bash
# Build and run with docker-compose
docker-compose up --build

# Run in background
docker-compose up -d --build

# Stop the application
docker-compose down
```

### Docker Configuration

- **Base Image**: `maven:3.9-eclipse-temurin-17` for build, `tomcat:9.0-jdk17` for runtime
- **Port**: 8080
- **WAR File**: Built and copied to Tomcat webapps
- **Health Check**: Basic HTTP check on root path

## CI/CD Pipeline

This project uses GitHub Actions for continuous integration and deployment.

### GitHub Actions Workflow

The CI/CD pipeline includes:
- **Build**: Maven clean install
- **Unit Tests**: JUnit tests (excluding Selenium)
- **Integration Tests**: Selenium UI tests with Chrome
- **Code Quality**: SonarCloud analysis
- **Containerization**: Docker build and push
- **Deployment**: Automatic deploy to Render on main branch

### Required GitHub Secrets

Set these in your repository settings under Secrets and variables > Actions:

- **`SONAR_TOKEN`**: SonarCloud authentication token
- **`SONAR_PROJECT_KEY`**: Your SonarCloud project key
- **`SONAR_ORG`**: Your SonarCloud organization key
- **`DOCKERHUB_USERNAME`**: Docker Hub username
- **`DOCKERHUB_TOKEN`**: Docker Hub access token
- **`RENDER_DEPLOY_HOOK`**: Render deployment webhook URL

### Branch Protection

Configure branch protection rules for `main` and `dev` branches:
- Require pull request reviews
- Require status checks to pass
- Include administrators

### Quality Gates

- Code coverage: ≥50%
- Code smells: ≤10
- Critical bugs: 0
- Security hotspots: Reviewed

### Workflow Triggers

- Push to `main` or `dev` branches
- Pull requests targeting `main` or `dev` branches

## Support & Documentation

- **Maven Documentation**: https://maven.apache.org/
- **Tomcat Documentation**: https://tomcat.apache.org/
- **Selenium Documentation**: https://www.selenium.dev/
- **Servlet Documentation**: https://docs.oracle.com/javaee/7/api/javax/servlet/

## License

This project is created for educational and learning purposes.

## Author

Built as a comprehensive DevOps project demonstrating:
- Full-stack web application development
- Maven-based project management
- Automated testing with Selenium
- Responsive web design
- RESTful API design
- Session management

  Tested and verified: April 2026
- Clean code architecture

---

**Happy Library Managing! 📚**



#Demo
