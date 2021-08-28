package TcWeb.service;

import TcWeb.DataBase.DBUtilsDao;
import TcWeb.DataBase.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class mailboxVerification extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sql = "insert into user (username, password, email) values (?, ?, ?)";
        if (request.getParameter("Validation").equals(session.getAttribute("verificationCode"))) {
            User user = (User) session.getAttribute("user1");
            session.removeAttribute("verificationCode");
            try {
                new DBUtilsDao().insert(user, sql);
                session.removeAttribute("user");
                response.sendRedirect("html/login.html");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("html/mailboxVerification.html?errorCode=3");
        }
    }
}
