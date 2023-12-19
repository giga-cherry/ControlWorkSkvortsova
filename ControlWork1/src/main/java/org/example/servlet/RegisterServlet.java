package org.example.servlet;

import org.example.service.AccountServiceImpl;
import org.example.validator.InputValidator;
import org.example.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {


    private final AccountServiceImpl service = new AccountServiceImpl();

    private final UserValidator userValidator = new UserValidator();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usernameValid = InputValidator.validate(req.getParameter("usernameR"));

        if (!(userValidator.userNameInBase(usernameValid))) {
            service.registerAccount(usernameValid, req.getParameter("passwordR"));
            resp.sendRedirect("/login");
        } else {
            req.getSession().setAttribute("nameIsPresent", "занято");
            resp.sendRedirect("/register");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.ftl").forward(req, resp);
    }
}

