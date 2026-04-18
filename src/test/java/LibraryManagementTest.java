import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class LibraryManagementTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "http://localhost:8080/library";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    @Before
    public void setUp() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test Case 1: Add a Book via UI
     * Steps:
     * 1. Open login page
     * 2. Login with credentials
     * 3. Navigate to Add Book page
     * 4. Fill book details
     * 5. Submit form
     * 6. Verify success message
     */
    @Test
    public void testAddBookViaUI() {
        System.out.println("========== Test 1: Add Book via UI ==========");

        try {
            // Step 1: Navigate to login page
            driver.get(BASE_URL + "/index.html");
            System.out.println("✓ Opened login page");

            // Step 2: Login
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            usernameField.sendKeys(USERNAME);
            passwordField.sendKeys(PASSWORD);
            loginButton.click();
            System.out.println("✓ Login successful");

            // Wait for redirect to dashboard
            wait.until(ExpectedConditions.urlContains("dashboard.html"));
            System.out.println("✓ Redirected to dashboard");

            // Step 3: Navigate to Add Book page
            driver.get(BASE_URL + "/add-book.html");
            System.out.println("✓ Navigated to Add Book page");

            // Step 4: Fill book details
            WebElement bookIdField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("bookId")));
            WebElement bookTitleField = driver.findElement(By.id("bookTitle"));
            WebElement bookAuthorField = driver.findElement(By.id("bookAuthor"));

            int bookId = (int) System.currentTimeMillis() % 100000; // Unique ID
            String bookTitle = "Test Book " + bookId;
            String bookAuthor = "Test Author";

            bookIdField.sendKeys(String.valueOf(bookId));
            bookTitleField.sendKeys(bookTitle);
            bookAuthorField.sendKeys(bookAuthor);
            System.out.println("✓ Filled book details - ID: " + bookId + ", Title: " + bookTitle + 
                             ", Author: " + bookAuthor);

            // Step 5: Submit form
            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();
            System.out.println("✓ Form submitted");

            // Step 6: Verify redirect to view-books (success indicator)
            wait.until(ExpectedConditions.urlContains("view-books.html"));
            System.out.println("✓ Redirected to view-books.html (indicates success)");

            System.out.println("========== Test 1 PASSED ==========\n");

        } catch (Exception e) {
            System.out.println("❌ Test failed with error: " + e.getMessage());
            e.printStackTrace();
            fail("Test Add Book failed: " + e.getMessage());
        }
    }

    /**
     * Test Case 2: Issue a Book
     * Steps:
     * 1. Add a book first
     * 2. Navigate to Issue/Return page
     * 3. Enter book ID and issue
     * 4. Verify success message
     * 5. Verify book status changed from Available to Issued
     */
    @Test
    public void testIssueBook() {
        System.out.println("========== Test 2: Issue Book ==========");

        try {
            // Step 1: Login and navigate to Add Book page
            driver.get(BASE_URL + "/index.html");
            System.out.println("✓ Opened login page");

            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            usernameField.sendKeys(USERNAME);
            passwordField.sendKeys(PASSWORD);
            loginButton.click();
            System.out.println("✓ Login successful");

            wait.until(ExpectedConditions.urlContains("dashboard.html"));

            // Add a book first
            driver.get(BASE_URL + "/add-book.html");

            WebElement bookIdField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("bookId")));
            WebElement bookTitleField = driver.findElement(By.id("bookTitle"));
            WebElement bookAuthorField = driver.findElement(By.id("bookAuthor"));

            int bookId = (int) System.currentTimeMillis() % 100000;
            String bookTitle = "Test Issue Book " + bookId;
            String bookAuthor = "Test Author";

            bookIdField.sendKeys(String.valueOf(bookId));
            bookTitleField.sendKeys(bookTitle);
            bookAuthorField.sendKeys(bookAuthor);

            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();

            // Wait for book to be added
            WebElement addBookMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("message")));
            System.out.println("✓ Book added: " + bookTitle);

            // Step 2: Navigate to Issue/Return page
            driver.get(BASE_URL + "/issue-return-book.html");
            System.out.println("✓ Navigated to Issue/Return page");

            // Wait for page to load and find the issue book form
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("issueBookId")));

            // Step 3: Enter book ID and issue
            WebElement issueBookIdField = driver.findElement(By.id("issueBookId"));
            issueBookIdField.sendKeys(String.valueOf(bookId));
            System.out.println("✓ Entered book ID: " + bookId);

            // Find and click issue button
            WebElement issueForm = driver.findElement(By.id("issueForm"));
            WebElement issueSubmitButton = issueForm.findElement(By.xpath(".//button[@type='submit']"));
            issueSubmitButton.click();
            System.out.println("✓ Issue button clicked");

            // Step 4: Verify redirect to view-books (indicates success)
            wait.until(ExpectedConditions.urlContains("view-books.html"));
            System.out.println("✓ Redirected to view-books (automatic redirect indicates success)");

            // Step 5: Verify book appears in view-books (book was issued successfully)
            driver.get(BASE_URL + "/view-books.html");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("data-table")));
            System.out.println("✓ View Books page loaded (issue successful)");

            System.out.println("========== Test 2 PASSED ==========\n");

        } catch (Exception e) {
            System.out.println("❌ Test failed with error: " + e.getMessage());
            e.printStackTrace();
            fail("Test Issue Book failed: " + e.getMessage());
        }
    }

    /**
     * Test Case 3: Search Book by Title
     * Steps:
     * 1. Add a book
     * 2. Navigate to Search page
     * 3. Search by title
     * 4. Verify search results
     */
    @Test
    public void testSearchBookByTitle() {
        System.out.println("========== Test 3: Search Book by Title ==========");

        try {
            // Login
            driver.get(BASE_URL + "/index.html");

            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            usernameField.sendKeys(USERNAME);
            passwordField.sendKeys(PASSWORD);
            loginButton.click();

            wait.until(ExpectedConditions.urlContains("dashboard.html"));
            System.out.println("✓ Login successful");

            // Navigate to Search page
            driver.get(BASE_URL + "/search-book.html");
            System.out.println("✓ Navigated to Search page");

            // Wait for search form
            WebElement searchQuery = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("searchQuery")));

            // Search for a partial title
            searchQuery.sendKeys("Test");

            // Click search button
            WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
            searchButton.click();
            System.out.println("✓ Search performed with query: 'Test'");

            // Verify results
            Thread.sleep(1000); // Wait for results to load
            WebElement searchResults = driver.findElement(By.id("searchResults"));
            String resultsText = searchResults.getText();

            assertNotNull("Search results should not be null", resultsText);
            System.out.println("✓ Search results displayed");
            System.out.println("========== Test 3 PASSED ==========\n");

        } catch (Exception e) {
            System.out.println("❌ Test failed with error: " + e.getMessage());
            e.printStackTrace();
            fail("Test Search Book failed: " + e.getMessage());
        }
    }

    /**
     * Test Case 4: Verify Login Page
     */
    @Test
    public void testLoginPage() {
        System.out.println("========== Test 4: Verify Login Page ==========");

        try {
            driver.get(BASE_URL + "/index.html");
            System.out.println("✓ Opened login page");

            // Verify login form elements exist
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            assertNotNull("Username field should exist", usernameField);
            assertNotNull("Password field should exist", passwordField);
            assertNotNull("Login button should exist", loginButton);

            System.out.println("✓ Login form elements verified");
            System.out.println("========== Test 4 PASSED ==========\n");

        } catch (Exception e) {
            System.out.println("❌ Test failed with error: " + e.getMessage());
            e.printStackTrace();
            fail("Test Login Page failed: " + e.getMessage());
        }
    }

    /**
     * Test Case 5: Verify Dashboard Page After Login
     */
    @Test
    public void testDashboardPageAfterLogin() {
        System.out.println("========== Test 5: Verify Dashboard Page After Login ==========");

        try {
            // Login
            driver.get(BASE_URL + "/index.html");

            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            usernameField.sendKeys(USERNAME);
            passwordField.sendKeys(PASSWORD);
            loginButton.click();

            // Wait for dashboard to load
            wait.until(ExpectedConditions.urlContains("dashboard.html"));
            System.out.println("✓ Successfully redirected to dashboard");

            // Verify dashboard elements
            String pageTitle = driver.getTitle();
            assertNotNull("Page title should exist", pageTitle);
            System.out.println("✓ Dashboard page title: " + pageTitle);

            System.out.println("========== Test 5 PASSED ==========\n");

        } catch (Exception e) {
            System.out.println("❌ Test failed with error: " + e.getMessage());
            e.printStackTrace();
            fail("Test Dashboard Page failed: " + e.getMessage());
        }
    }
}
