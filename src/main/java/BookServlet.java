import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/api/books")
public class BookServlet extends HttpServlet {
    private Library library = Library.getInstance();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if (!isUserLoggedIn(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.println("{\"error\": \"Please login first\", \"success\": false}");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("all".equals(action)) {
                ArrayList<Book> books = library.getAllBooks();
                out.println(gson.toJson(books));

            } else if ("search".equals(action)) {
                String query = request.getParameter("query");
                String type = request.getParameter("type");

                ArrayList<Book> results;
                if ("author".equals(type)) {
                    results = library.searchByAuthor(query);
                } else {
                    results = library.searchByTitle(query);
                }
                out.println(gson.toJson(results));

            } else if ("get".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Book book = library.getBookById(id);

                if (book != null) {
                    out.println(gson.toJson(book));
                } else {
                    out.println("{\"error\": \"Book not found\"}");
                }

            } else {
                // 🔥 FIX: handle invalid or null action
                out.println("{\"error\": \"Invalid action\"}");
            }

        } catch (Exception e) {
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if (!isUserLoggedIn(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.println("{\"error\": \"Please login first\", \"success\": false}");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String author = request.getParameter("author");

                Book book = new Book(id, title, author);
                boolean added = library.addBook(book);
                if (added) {
                    out.println("{\"success\": true, \"message\": \"Book added successfully\"}");
                } else {
                    out.println("{\"success\": false, \"message\": \"Book ID already exists!\"}");
                }

            } else if ("issue".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean success = library.issueBook(id);

                if (success) {
                    out.println("{\"success\": true, \"message\": \"Book issued successfully\"}");
                } else {
                    out.println("{\"success\": false, \"message\": \"Book already issued or not found\"}");
                }

            } else if ("return".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean success = library.returnBook(id);

                if (success) {
                    out.println("{\"success\": true, \"message\": \"Book returned successfully\"}");
                } else {
                    out.println("{\"success\": false, \"message\": \"Book not found or not issued\"}");
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean success = library.deleteBook(id);

                if (success) {
                    out.println("{\"success\": true, \"message\": \"Book deleted successfully\"}");
                } else {
                    out.println("{\"success\": false, \"message\": \"Book not found\"}");
                }

            } else {
                // 🔥 FIX: handle invalid or null action
                out.println("{\"success\": false, \"message\": \"Invalid action\"}");
            }

        } catch (NumberFormatException e) {
            out.println("{\"success\": false, \"message\": \"Invalid input\"}");
        } catch (Exception e) {
            out.println("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("user") != null;
    }
}