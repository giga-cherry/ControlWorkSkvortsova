package org.example.servlet;

import org.example.dao.AccountDao;
import org.example.dao.AccountDaoImpl;
import org.example.model.Account;
import org.example.service.AccountServiceImpl;
import org.example.util.PasswordUtil;
import org.example.validator.InputValidator;
import org.example.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class SignInServlet extends HttpServlet {

    private final UserValidator userValidator = new UserValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usernameValid = InputValidator.validate(req.getParameter("usernameL"));
        Account user = new Account(usernameValid, PasswordUtil.encrypt(req.getParameter("passwordL")), new ArrayList<>());
        if (userValidator.userInBase(user,req)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setMaxInactiveInterval(60 * 60);

            if ("on".equals(req.getParameter("mark"))){
                //cookie(на клиенте, заканчивается после срока жизни)
                Cookie cookie = new Cookie(user.getUsername(),user.getPassword());
                cookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(cookie);
            }

            resp.sendRedirect("/");

        } else {
            req.getSession().setAttribute("incorrect", "incorrect");
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/login.ftl").forward(req, resp);
    }
}

