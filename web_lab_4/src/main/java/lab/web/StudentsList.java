package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentsList
 */
public class StudentsList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentsList() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String lang = request.getParameter("lang");

        Locale locale;
        if (lang == null || lang.isEmpty()) {
            locale = new Locale("ru", "RU");           // по умолчанию русский
        } else if ("en".equalsIgnoreCase(lang)) {
            locale = Locale.ENGLISH;
        } else if ("ru".equalsIgnoreCase(lang)) {
            locale = new Locale("ru", "RU");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
                    "Параметр lang может принимать значение en или ru");
            return;
        }

        ResourceBundle res = ResourceBundle.getBundle("Students", locale);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head><title>" + res.getString("title") + "</title></head>");
            out.println("<body>");

            String header = res.getString("header") + " "
                    + (name != null ? name : res.getString("noName"));
            out.println("<h1>" + header + "</h1>");

            out.println("<table border='1' cellpadding='5'>");
            out.println("<tr>"
                    + "<td><b>" + res.getString("student.fio") + "</b></td>"
                    + "<td><b>" + res.getString("student.class") + "</b></td>"
                    + "<td><b>" + res.getString("student.present") + "</b></td>"
                    + "</tr>");

            out.println("<tr><td>" + res.getString("student1.fio") + "</td><td>"
                    + res.getString("student1.class") + "</td><td>"
                    + res.getString("student1.present") + "</td></tr>");

            out.println("<tr><td>" + res.getString("student2.fio") + "</td><td>"
                    + res.getString("student2.class") + "</td><td>"
                    + res.getString("student2.present") + "</td></tr>");

            out.println("<tr><td>" + res.getString("student3.fio") + "</td><td>"
                    + res.getString("student3.class") + "</td><td>"
                    + res.getString("student3.present") + "</td></tr>");

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}