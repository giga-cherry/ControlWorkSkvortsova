package org.example.servlet;

import org.example.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/primeTourBooking")
public class PrimeTourServlet extends HttpServlet {

    private static final BookingService service = BookingService.getInstance();

    private static final BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("exhibition", service.getBookingById(req));
            if (req.getSession().getAttribute("user")!=null){
                req.getSession().setAttribute("bookingList", bookingService.getBookingByUserIdAndPCId(req));
            }
        } catch (RuntimeException e){
            req.getSession().setAttribute("exhibition",null);
        }

        req.getRequestDispatcher("/primeTourBooking.ftl").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookingService.createBooking(req);
        resp.sendRedirect("/primeTourBooking?exhibition_id="+req.getParameter("exhibition_id"));
    }
}

