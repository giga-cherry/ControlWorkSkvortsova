package org.example.servlet;

import org.example.model.Account;
import org.example.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    private final BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookingService.deleteBooking(req);
        req.getSession().setAttribute("tickets", bookingService.getBookingByUserId(((Account)(req.getSession().getAttribute("user"))).getId()));
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }
}

